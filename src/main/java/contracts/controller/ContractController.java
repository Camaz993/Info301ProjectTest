package contracts.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.Expired;
import contracts.domain.Favourited;
import contracts.domain.InNegotiation;
import contracts.domain.Operative;
import contracts.domain.RelatedAgreements;
import contracts.domain.Status;
import contracts.domain.StatusLink;
import contracts.domain.User;
import contracts.repository.ContractRepository;
import contracts.repository.CurrentRepository;
import contracts.repository.ExpiredRepository;
import contracts.repository.InNegotiationRepository;
import contracts.repository.OperativeRepository;
import contracts.repository.StatusLinkRepository;
import contracts.service.AuditService;
import contracts.service.CurrentService;
import contracts.service.IAccountService;
import contracts.service.IContractService;
import contracts.service.IExpiredService;
import contracts.service.IFavouritedService;
import contracts.service.IInNegotiationService;
import contracts.service.IOperativeService;
import contracts.service.IRelatedAgreementsService;
import contracts.service.IStatusLinkService;

@Controller
public class ContractController {
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private IContractService contractService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IOperativeService operativeService;
	
	@Autowired
	private IInNegotiationService in_negotiationService;
	
	@Autowired
	private IExpiredService expiredService;
	
	@Autowired
	private IStatusLinkService statuslinkService;
	
	@Autowired
	private IFavouritedService favouritedService;
	
	@Autowired
	private ContractRepository repo;
	
	@Autowired
	private IRelatedAgreementsService relatedAgreementsService;
	
	@Autowired
	private InNegotiationRepository negRepo;
	
	@Autowired
	private OperativeRepository opRepo;
	
	@Autowired
	private ExpiredRepository exRepo;
	
	@Autowired
	private StatusLinkRepository slRepo;

    @Autowired
	private CurrentService currentService;
	
	@Autowired
	private CurrentRepository currentRepository;
	
	//See stack overflow: 
	//https://stackoverflow.com/questions/11271554/compare-two-objects-in-java-with-possible-null-values/11271611
	public static boolean compare(String str1, String str2) {
	    return (str1 == null ? str2 == null : str1.equals(str2));
	}
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		return user;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@GetMapping("/add_contracts")
    public String showSignUpForm(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contract", new Contract());
		model.addAttribute("status", new Status());
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
        return "add_contracts";
    }
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@GetMapping("/add_status")
	public String showStatusForm(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		Integer requestid = contractService.findNewestContract();
		model.addAttribute("requestid", requestid);
		model.addAttribute("in_negotiation", new InNegotiation());
		model.addAttribute("operative", new Operative());
		model.addAttribute("expired", new Expired());
		repo.findById(requestid).ifPresent(contract->model.addAttribute("selectedContract", contract));
		return "add_status";
	}
	
	//add a new contract to the database
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/api/contracts")
	public String addContract(@Valid @ModelAttribute(name="contract") Contract contract, BindingResult br, Model model)
	{
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
		if(br.hasErrors()) {
		model.addAttribute("message2", "There have been errors processing your contract. Please see tabs below.");
		return "add_contracts";
		}
		LocalDateTime date = LocalDateTime.now();
		contract.setArchived("F");
		contract.setDate_updated(date);
		contractService.addContract(contract);
		auditService.addAuditDetails(contract, getCurrentUser());
		return "redirect:/add_status";
	}
	
	//add an in negotiation status to the database, along with null operative and expired status'
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/api/in_negotiation")
	public String add_in_negotiation(@ModelAttribute(name="in_negotiation") InNegotiation in_negotiation, RedirectAttributes redirectAttributes, Model model)
	{
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		Integer requestid = contractService.findNewestContract();
		in_negotiation.setRequestId(requestid);
		in_negotiationService.addInNegotiation(in_negotiation);
		Operative op = new Operative();
		op.setRequestId(requestid);
		operativeService.addOperative(op);
		Expired ex = new Expired();
		ex.setRequestId(requestid);
		expiredService.addExpired(ex);
		StatusLink statuslink = new StatusLink(requestid, "in_negotiation");
		statuslinkService.addStatusLink(statuslink);
		redirectAttributes.addFlashAttribute("message", "Contract successfully added");
		return "redirect:/view_details/" + requestid;
	}
	
	//add an operative status to the db, along with null in negotiation and expired status'
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL" })
	@PostMapping("/api/operative")
	public String add_operative(@ModelAttribute(name="operative") Operative operative, RedirectAttributes redirectAttributes, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		Integer requestid = contractService.findNewestContract();
		operative.setRequestId(requestid);		
		operativeService.addOperative(operative);
		Expired ex = new Expired();
		ex.setRequestId(requestid);
		expiredService.addExpired(ex);
		InNegotiation neg = new InNegotiation();
		neg.setRequestId(requestid);
		in_negotiationService.addInNegotiation(neg);
		StatusLink statuslink = new StatusLink(requestid, "operative");
		statuslinkService.addStatusLink(statuslink);
		redirectAttributes.addFlashAttribute("message", "Contract successfully added");
		return "redirect:/view_details/" + requestid;
	}
	
	//add an expired status in the db, along with in negotiation and operative status'
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/api/expired")
	public String add_expired(@ModelAttribute(name="expired") Expired expired, RedirectAttributes redirectAttributes, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		Integer requestid = contractService.findNewestContract();
		expired.setRequestId(requestid);
		expiredService.addExpired(expired);
		InNegotiation neg = new InNegotiation();
		neg.setRequestId(requestid);
		in_negotiationService.addInNegotiation(neg);
		Operative op = new Operative();
		op.setRequestId(requestid);
		operativeService.addOperative(op);
		StatusLink statuslink = new StatusLink(requestid, "expired");
		statuslinkService.addStatusLink(statuslink);
		redirectAttributes.addFlashAttribute("message", "Contract successfully added");
		return "redirect:/view_details/" + requestid;
	}
	
	@GetMapping("/search_contracts_all")
	public String getAllContracts(Model model) {
		List<Contract> allContracts = contractService.getAllContracts();
		List favStatus = new ArrayList<>();
		User user = accountService.findUser(getCurrentUser().getUsername());
		for (int i = 0; i < allContracts.size(); i++) {
			if (contractService.checkFavourited(allContracts.get(i).getRequestid(), user.getUserid())) {
				favStatus.add("favourited");
			}
			else {
				favStatus.add("unfavourited");
			}
		}
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contracts", contractService.getAllContracts());
		model.addAttribute("favstatus", favStatus);
		return "search_contracts";
	}
	
	//Method to bring up search results and checks if user has item favourited or not.
	//If they have the item favourited, the button dynamically updates to unfavourited.
	@GetMapping("/search_contracts")
	public String getAllContractsShort(Model model) {
		List<Contract> allContracts = contractService.getContractsShortList();
		List favStatus = new ArrayList<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		for (int i = 0; i < allContracts.size(); i++) {
			if (contractService.checkFavourited(allContracts.get(i).getRequestid(), user.getUserid())) {
				favStatus.add("favourited");
			}
			else {
				favStatus.add("unfavourited");
			}
		}
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contracts", contractService.getContractsShortList());
		model.addAttribute("favstatus", favStatus);
		return "search_contracts";
	}
	
	
	@GetMapping("/")
	public String mostRecent(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contracts", contractService.getAllContracts());
		model.addAttribute("contracts2", contractService.getNullUserContracts());
		return "index";
	}
	
	//assign a specific user to a contract
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/assign/{requestid}")
	public String assignUser(@PathVariable("requestid") int requestid, Model model) {
		String fieldUpdatedList = "";
		String fieldBeforeList = "";
		String fieldAfterList = "";
		Contract foundContract = contractService.findContract(requestid).orElse(new Contract());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		Audit blank = new Audit();
		if (!compare(String.valueOf(foundContract.getUser()),String.valueOf(user))) {
			if (foundContract.getUser() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getUser().getUserid()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (user != null) {
				fieldAfterList += ((String.valueOf((user.getUserid()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("userid") + (", ");
		}
			LocalDateTime date = LocalDateTime.now();
			foundContract.setDate_updated(date);
			blank.setField_after(fieldAfterList);
			blank.setField_before(fieldBeforeList);
			blank.setField_updated(fieldUpdatedList);
			blank.setUserid(getCurrentUser());
			blank.setRequestedid(foundContract);
			blank.setDate(date);
			foundContract.setArchived("F");
			foundContract.setUserid(user);
			auditService.addAudit(blank);
			contractService.update(foundContract);
		return "redirect:/my_contracts";
	}
	
	@GetMapping("/contracts/search")
	public String searchContracts(ModelMap map, @RequestParam String search, Model model)
	{
		List<Contract> allContracts = contractService.getAllContracts();
		List favStatus = new ArrayList<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		for (int i = 0; i < allContracts.size(); i++) {
			if (contractService.checkFavourited(allContracts.get(i).getRequestid(), user.getUserid())) {
				favStatus.add("favourited");
			}
			else {
				favStatus.add("unfavourited");
			}
		}
		

		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		map.put("search", search);
		model.addAttribute("contracts", contractService.searchContracts(search));	
		model.addAttribute("favstatus", favStatus);
		//return contractService.searchContracts(search);
		return "search_contracts";
	}
	
	@GetMapping("/contracts/sorted")
	public String allContractsSorted(Model model)
	{
		List<Contract> allContracts = contractService.getContractsSorted();
		List favStatus = new ArrayList<>();
		User user = accountService.findUser(getCurrentUser().getUsername());
		for (int i = 0; i < allContracts.size(); i++) {
			if (contractService.checkFavourited(allContracts.get(i).getRequestid(), user.getUserid())) {
				favStatus.add("favourited");
			}
			else {
				favStatus.add("unfavourited");
			}
		}
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contracts", contractService.getContractsSorted());
		model.addAttribute("favstatus", favStatus);
		return "search_contracts";
	}
	
	@GetMapping("/contracts/sortedparty")
	public String allContractsSortedParty(Model model)
	{
		List<Contract> allContracts = contractService.getContractsSortedParty();
		List favStatus = new ArrayList<>();
		User user = accountService.findUser(getCurrentUser().getUsername());
		for (int i = 0; i < allContracts.size(); i++) {
			if (contractService.checkFavourited(allContracts.get(i).getRequestid(), user.getUserid())) {
				favStatus.add("favourited");
			}
			else {
				favStatus.add("unfavourited");
			}
		}
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contracts", contractService.getContractsSortedParty());
		model.addAttribute("favstatus", favStatus);
		return "search_contracts";
	}
	
	@GetMapping("/contract/{requestid}")
	@ResponseBody 
	public Optional<Contract> getContract(@PathVariable("requestid") int requestid) {
		return repo.findById(requestid);
	}
	
	@GetMapping("/view_details/{requestid}")
	public String selectedContract(@PathVariable("requestid") int requestid, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		repo.findById(requestid).ifPresent(o->model.addAttribute("selectedContract", o));
		slRepo.findById(requestid).ifPresent(o->model.addAttribute("status", o));
		opRepo.findById(requestid).ifPresent(o->model.addAttribute("operative", o));
		exRepo.findById(requestid).ifPresent(o->model.addAttribute("expired", o));
		negRepo.findById(requestid).ifPresent(o->model.addAttribute("in_negotiation", o));
		String favStatus = "";
		if (contractService.checkFavourited(requestid, getCurrentUser().getUserid())) {
				favStatus = "favourited";
			}
			else {
				favStatus = "unfavourited";
		}
		model.addAttribute("favstatus", favStatus);
		model.addAttribute("contracts", contractService.getRelatedContracts(requestid));
		model.addAttribute("audits", auditService.getContractsByAuditsRequestID(requestid));
		return "view_details";
	}
	
	//get the contract object from the db to update
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@GetMapping("/update_details/{requestid}")
	public String updateContractForm(@PathVariable("requestid") int requestid, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		repo.findById(requestid).ifPresent(contract->model.addAttribute("contract", contract));
		negRepo.findById(requestid).ifPresent(in_negotiation->model.addAttribute("in_negotiation", in_negotiation));
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
		return "update_details";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@GetMapping("/update_status/{requestid}")
	public String updateStatus(@PathVariable("requestid") int requestid, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		negRepo.findById(requestid).ifPresent(in_negotiation->model.addAttribute("in_negotiation", in_negotiation));
		opRepo.findById(requestid).ifPresent(operative->model.addAttribute("operative", operative));
		exRepo.findById(requestid).ifPresent(expired->model.addAttribute("expired", expired));
		repo.findById(requestid).ifPresent(o->model.addAttribute("selectedContract", o));
		return "update_status";	
	}
	
	//updates an in negotiation status 
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL" })
	@PostMapping("/api/update/in_negotiation")
	public String updateInNegotiation(@ModelAttribute(name="in_negotiation") InNegotiation in_negotiation, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		in_negotiationService.update(in_negotiation);
		Integer requestid = in_negotiation.getRequestId();
		StatusLink stat = new StatusLink(requestid, "in_negotiation");
		statuslinkService.update(stat);
		return "redirect:/";
	}
	
	//updates an operative status
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL" })
	@PostMapping("/api/update/operative")
	public String updateOperative(@ModelAttribute(name="operative") Operative operative, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		operativeService.update(operative);
		Integer requestid = operative.getRequestId();
		StatusLink stat = new StatusLink(requestid, "operative");
		statuslinkService.update(stat);
		return "redirect:/search_contracts";
	}
	
	//updates an expired status
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/api/update/expired")
	public String updateExpired(@ModelAttribute(name="expired") Expired expired, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		expiredService.update(expired);
		Integer requestid = expired.getRequestId();
		StatusLink stat = new StatusLink(requestid, "expired");
		statuslinkService.update(stat);
		return "redirect:/search_contracts";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/archive_contracts/{requestid}")
	public String archiveContract(@PathVariable("requestid") int requestid, Model model) {
		Contract foundContract = contractService.findContract(requestid).orElse(new Contract());
		foundContract.setArchived("T");
		auditService.addAuditArchived(foundContract, getCurrentUser());
		contractService.addContract(foundContract);
		return "redirect:/search_contracts";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@GetMapping("/archive_contracts")
	public String getArchivedContracts(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contracts", contractService.getArchivedContracts());
		return "archive_contracts";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/unarchive_contracts/{requestid}")
	public String unarchiveContract(@PathVariable("requestid") int requestid, Model model) {
		Contract unarchiveContract = contractService.findContract(requestid).orElse(new Contract());
		unarchiveContract.setArchived("F");
		contractService.addContract(unarchiveContract);
		return "redirect:/search_contracts";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@GetMapping("/unarchive_contracts")
	public String getUnarchivedContracts(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("contracts", contractService.getAllContracts());
		return "search_contracts";
	}
	
	@GetMapping("/my_contracts")
    public String showMyContracts(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		model.addAttribute("contracts", contractService.getContractsByUser(user.getUserid()));
		model.addAttribute("currentuser", user.getUsername());
        return "my_contracts";
    }
	
	//adds a contract to a users favourited contracts
	@PostMapping("/favourite_contracts/{requestid}")
	public String favouritedContract(@PathVariable("requestid") int requestid, Model model) {
		Contract favouritedContract = contractService.findContract(requestid).orElse(new Contract());
		Favourited favourite = new Favourited();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		favourite.setUser(user);
		favourite.setContract(favouritedContract);
		favouritedService.addFavourite(favourite);
		return "redirect:/search_contracts";
	}

	@GetMapping("/favourite_contracts")
	public String getFavouritedContracts(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		model.addAttribute("contracts", contractService.getFavouritedContracts(user.getUserid()));
		model.addAttribute("currentuser", user.getUsername());
		return "favourite_contracts";
	}
	
	@GetMapping("/add_related/{requestid}")
	public String relatedContracts(@PathVariable("requestid") int requestid, Model model) {
		repo.findById(requestid).ifPresent(o->model.addAttribute("selectedContract", o));
		model.addAttribute("contracts", contractService.getAllExceptCurrent(requestid));
		Contract newContract = contractService.findContract(requestid).orElse(new Contract()); 
		model.addAttribute("currentContract", newContract.getAgreement_title());
		RelatedAgreements relatedAgreement = new RelatedAgreements();
		Contract related = contractService.findContract(requestid).orElse(new Contract());
		relatedAgreement.setRequestid_related(related);
		relatedAgreementsService.addRelatedAgreements(relatedAgreement);
		return "add_related";
	}
	
	@PostMapping("/related_contracts/{requestid}")
	public String getRelatedContracts(@PathVariable("requestid") int requestid, Model model) {
		Contract relatedContract = contractService.findContract(requestid).orElse(new Contract());
		Integer relatedid = relatedAgreementsService.findNewestRelated();
		RelatedAgreements relatedAgreement = relatedAgreementsService.findbyId(relatedid).orElse(new RelatedAgreements());
		relatedAgreement.setRequestid_relatedto(requestid);
		relatedAgreementsService.addRelatedAgreements(relatedAgreement);
		Contract newRelated = relatedAgreement.getRequestid_related();
		return "redirect:/view_details/" + newRelated.getRequestid();
	}
	
	@PostMapping("/unrelate_contracts/{request}/{requestid}")
	public String unrelateContracts(@PathVariable("request") int request, @PathVariable("requestid") int requestid, Model model) {
		contractService.unrelateContract(requestid, request);
		return "redirect:/view_details/" + requestid;
	}
	
	//removes a contract from a users favourite contracts
	@PostMapping("/unfavourite_contracts/{requestid}")
	public String unfavouritContract(@PathVariable("requestid") int requestid, Model model) {
		Contract unfavouriteContract = contractService.findContract(requestid).orElse(new Contract());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		contractService.unfavouriteContract(unfavouriteContract.getRequestid(), user.getUserid());
		contractService.addContract(unfavouriteContract);
		return "redirect:/search_contracts";
	}
	
	@GetMapping("help")
	public String help(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		return "help";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@GetMapping("/reassign/{requestid}")
	public String reassignContractForm(@PathVariable("requestid") int requestid, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		repo.findById(requestid).ifPresent(contract->model.addAttribute("contract", contract));
		negRepo.findById(requestid).ifPresent(in_negotiation->model.addAttribute("in_negotiation", in_negotiation));
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
		return "reassign";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_LEGAL"  })
	@PostMapping("/api/reassignment")
	public String reassignContract(@Valid @ModelAttribute(name="contract") Contract contract, BindingResult br, Model model)
	{	
	Integer i = currentService.getCurrent();
	currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
	String fieldUpdatedList = "";
	String fieldBeforeList = "";
	String fieldAfterList = "";
	Contract foundContract = contractService.findContract(contract.getRequestid()).orElse(new Contract());
	Audit blank = new Audit();
	if (!compare(String.valueOf(foundContract.getUser()),String.valueOf(contract.getUser()))) {
		if (foundContract.getUser() != null) {
			fieldBeforeList += ((String.valueOf((foundContract.getUser().getUserid()))))+ (", ");
		}
		else {
			fieldBeforeList += (" ")+ (", ");
		}
		if (contract.getUser() != null) {
			fieldAfterList += ((String.valueOf((contract.getUser().getUserid()))))+ (", ");
		}
		else {
			fieldAfterList += (" ")+ (", ");
		}
		fieldUpdatedList += ("userid") + (", ");
	}
		LocalDateTime date = LocalDateTime.now();
		contract.setDate_updated(date);
		blank.setField_after(fieldAfterList);
		blank.setField_before(fieldBeforeList);
		blank.setField_updated(fieldUpdatedList);
		blank.setUserid(getCurrentUser());
		blank.setRequestedid(contract);
		blank.setDate(date);
		contract.setArchived("F");
		contractService.update(contract);
		auditService.addAudit(blank);
		return "redirect:/view_details/" + contract.getRequestid();
	}

}
