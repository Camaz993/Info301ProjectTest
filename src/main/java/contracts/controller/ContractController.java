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

import contracts.domain.Contract;
import contracts.domain.Status;
import contracts.domain.User;
import contracts.repository.ContractRepository;
import contracts.service.IContractService;

@Controller
public class ContractController {
	
	@Autowired
	private IContractService contractService;
	
	@Autowired
	private ContractRepository repo;
	
	@GetMapping("/add_contracts")
    public String showSignUpForm(Model model) {
		model.addAttribute("contract", new Contract());
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
		return "/index";
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

}
	
