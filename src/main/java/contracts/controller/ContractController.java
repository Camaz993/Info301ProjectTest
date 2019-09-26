package contracts.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.Expired;
import contracts.domain.InNegotiation;
import contracts.domain.Operative;
import contracts.domain.Status;
import contracts.domain.User;
import contracts.repository.ContractRepository;
import contracts.service.AuditService;
import contracts.service.IAccountService;
import contracts.service.IContractService;

@Controller
public class ContractController {
	
	

	@Autowired
	private AuditService auditService;
	
	@Autowired
	private IContractService contractService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private ContractRepository repo;
	
	@GetMapping("/add_contracts")
    public String showSignUpForm(Model model) {
		model.addAttribute("contract", new Contract());
		model.addAttribute("innegotiation", new InNegotiation());
		model.addAttribute("operative", new Operative());
		model.addAttribute("expired", new Expired());
		model.addAttribute("status", new Status());
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
        return "add_contracts";
    }
	
	@PostMapping("/api/contracts")
	public String addContract(@Valid @ModelAttribute(name="contract") Contract contract, BindingResult br)
	{

		if(br.hasErrors()) {
		return "add_contracts";
		}
		contract.setArchived("F");
		Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		contract.setDate_updated(timeNow);
		contractService.addContract(contract);
		auditService.addAuditDetails(contract);
		
		return "redirect:/";
	}
	
	@GetMapping("/search_contracts")
	public String getAllContracts(Model model) {
		model.addAttribute("contracts", contractService.getAllContracts());
		return "search_contracts";
	}
	
	
	@GetMapping("/")
	public String mostRecent(Model model) {
		model.addAttribute("contracts", contractService.getAllContracts());
		model.addAttribute("contracts2", contractService.getNullUserContracts());
		return "/index";
	}
	
	@PostMapping("/assign/{requestid}")
	public String assignUser(@PathVariable("requestid") int requestid, Model model) {
		Contract foundContract = contractService.findContract(requestid).orElse(new Contract());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		foundContract.setUserid(user);
		contractService.update(foundContract);
		return "redirect:/my_contracts";
	}
	
	@PostMapping("/api/contracts/search")
	public List<Contract> searchContracts(@RequestParam String search)
	{
		return contractService.searchContracts(search);
	}
	
	@PostMapping("/api/contracts/search/location")
	public List<Contract> searchLocation(@RequestParam String search)
	{
		return contractService.searchLocation(search);
	}
	
	@PostMapping("/api/contracts/search/type")
	public List<Contract> searchContractType(@RequestParam String search)
	{
		return contractService.searchContractType(search);
	}

	@GetMapping("/api/contracts")
	public List<Contract> allContracts()
	{
		return contractService.getAllContracts();
	}
	
	@GetMapping("/contract/{requestid}")
	@ResponseBody 
	public Optional<Contract> getContract(@PathVariable("requestid") int requestid) {
		return repo.findById(requestid);
	}
	
	@GetMapping("/view_details/{requestid}")
	public String selectedContract(@PathVariable("requestid") int requestid, Model model) {
		repo.findById(requestid).ifPresent(o->model.addAttribute("selectedContract", o));
		return "view_details";
	}
	
	@GetMapping("/update_details/{requestid}")
	public String updateContractForm(@PathVariable("requestid") int requestid, Model model) {
		repo.findById(requestid).ifPresent(contract->model.addAttribute("contract", contract));
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
		return "update_details";
	}
	
	@PostMapping("/api/updates")
	public String updateDetails(@RequestParam Integer requestid,@RequestParam User user,@RequestParam String agreement_title,@RequestParam String agreement_type,
			@RequestParam String description,@RequestParam String agreement_location,@RequestParam String language,@RequestParam String region,@RequestParam String related_agreements,@RequestParam String archived,
			@RequestParam String favourited,@RequestParam Date date_updated)
	{	
		Contract foundContract = contractService.findContract(requestid).orElse(new Contract());
		Audit blank = new Audit();
		if (!foundContract.getRequestid().equals(requestid)) {
			blank.setField_before((String.valueOf((foundContract.getUser().getUserid()))));
			blank.setField_after((String.valueOf((user.getUserid()))));
			blank.setField_updated("userid");
		}
		Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		foundContract.setDate_updated(timeNow);
		foundContract.setUserid(user);
		foundContract.setAgreement_title(agreement_title);
		foundContract.setAgreement_type(agreement_type);
		foundContract.setDescription(description);
		foundContract.setAgreement_location(agreement_location);
		foundContract.setLanguage(language);
		foundContract.setRegion(region);
		foundContract.setRelated_agreements(related_agreements);
		foundContract.setArchived(archived);
		foundContract.setFavourited(favourited);
		foundContract.setDate_updated(date_updated);
		blank.setUserid(foundContract.getUser());
		blank.setRequestedid(foundContract);
		blank.setDate(foundContract.getDate_updated());
		contractService.update(foundContract);
		return "redirect:/";
	}
	
	@PostMapping("/archive_contracts/{requestid}")
	public String archiveContract(@PathVariable("requestid") int requestid, Model model) {
		Contract foundContract = contractService.findContract(requestid).orElse(new Contract());
		foundContract.setArchived("T");
		contractService.addContract(foundContract);
		return "redirect:/archive_contracts";
	}
	
	@GetMapping("/archive_contracts")
	public String getArchivedContracts(Model model) {
		model.addAttribute("contracts", contractService.getArchivedContracts());
		return "archive_contracts";
	}
	
	@PostMapping("/unarchive_contracts/{requestid}")
	public String unarchiveContract(@PathVariable("requestid") int requestid, Model model) {
		Contract unarchiveContract = contractService.findContract(requestid).orElse(new Contract());
		unarchiveContract.setArchived("F");
		contractService.addContract(unarchiveContract);
		return "redirect:/search_contracts";
	}
	
	@GetMapping("/unarchive_contracts")
	public String getUnarchivedContracts(Model model) {
		model.addAttribute("contracts", contractService.getAllContracts());
		return "search_contracts";
	}
	
	@GetMapping("/my_contracts")
    public String showMyContracts(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		model.addAttribute("contracts", contractService.getContractsByUser(user.getUserid()));
		model.addAttribute("currentuser", user.getUsername());
        return "my_contracts";
    }
	
	@PostMapping("/favourite_contracts/{requestid}")
	public String favouritedContract(@PathVariable("requestid") int requestid, Model model) {
		Contract favouritedContract = contractService.findContract(requestid).orElse(new Contract());
		favouritedContract.setFavourited("T");
		contractService.addContract(favouritedContract);
		return "redirect:/favourite_contracts";
	}

	@GetMapping("/favourite_contracts")
	public String getFavouritedContracts(Model model) {
		model.addAttribute("contracts", contractService.getFavouritedContracts());
		return "favourite_contracts";
	}
	
	@PostMapping("/unfavourite_contracts/{requestid}")
	public String unfavouritContract(@PathVariable("requestid") int requestid, Model model) {
		Contract unfavouriteContract = contractService.findContract(requestid).orElse(new Contract());
		unfavouriteContract.setFavourited("F");
		contractService.addContract(unfavouriteContract);
		return "redirect:/search_contracts";
	}
	

}
	
