package contracts.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.Expired;
import contracts.domain.Favourited;
import contracts.domain.InNegotiation;
import contracts.domain.Operative;
import contracts.domain.Status;
import contracts.domain.StatusLink;
import contracts.domain.User;
import contracts.repository.ContractRepository;
import contracts.service.AuditService;
import contracts.repository.ExpiredRepository;
import contracts.repository.InNegotiationRepository;
import contracts.repository.OperativeRepository;
import contracts.service.IAccountService;
import contracts.service.IContractService;
import contracts.service.IExpiredService;
import contracts.service.IFavouritedService;
import contracts.service.IInNegotiationService;
import contracts.service.IOperativeService;
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
	private InNegotiationRepository negRepo;
	
	@Autowired
	private OperativeRepository opRepo;
	
	@Autowired
	private ExpiredRepository exRepo;
	
	@GetMapping("/add_contracts")
    public String showSignUpForm(Model model) {
		model.addAttribute("contract", new Contract());
		model.addAttribute("status", new Status());
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
        return "add_contracts";
    }
	
	@GetMapping("/add_status")
	public String showStatusForm(Model model) {
		Integer requestid = contractService.findNewestContract();
		model.addAttribute("requestid", requestid);
		model.addAttribute("in_negotiation", new InNegotiation());
		model.addAttribute("operative", new Operative());
		model.addAttribute("expired", new Expired());
		return "add_status";
	}
	
	@PostMapping("/api/contracts")
	public String addContract(@Valid @ModelAttribute(name="contract") Contract contract, BindingResult br, Model model)
	{
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
		if(br.hasErrors()) {
		return "add_contracts";
		}
		contract.setArchived("F");
		Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		contract.setDate_updated(timeNow);
		contractService.addContract(contract);
		auditService.addAuditDetails(contract);
		return "redirect:/add_status";
	}
	
	@PostMapping("/api/in_negotiation")
	public String add_in_negotiation(@ModelAttribute(name="in_negotiation") InNegotiation in_negotiation)
	{
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
		return "redirect:/";
	}
	
	@PostMapping("api/operative")
	public String add_operative(@ModelAttribute(name="operative") Operative operative) {
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
		return "redirect:/";
	}
	
	@PostMapping("api/expired")
	public String add_expired(@ModelAttribute(name="expired") Expired expired) {
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
		negRepo.findById(requestid).ifPresent(in_negotiation->model.addAttribute("in_negotiation", in_negotiation));
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
		return "update_details";
	}
	
	@PostMapping("/api/updates")
	public String updateDetails(@Valid @ModelAttribute(name="contract") Contract contract, BindingResult br)
	{	
	String fieldUpdatedList = "";
	String fieldBeforeList = "";
	String fieldAfterList = "";
	Contract foundContract = contractService.findContract(contract.getRequestid()).orElse(new Contract());
	Audit blank = new Audit();
	if (!foundContract.getUser().equals(contract.getUser())) {
		fieldBeforeList += ((String.valueOf((foundContract.getUser().getUserid()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getUser().getUserid()))))+ (", ");
		fieldUpdatedList += ("userid") + (", ");
	}
	if (!foundContract.getAgreement_title().equals(contract.getAgreement_title())) {
		fieldBeforeList += ((String.valueOf((foundContract.getAgreement_title()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getAgreement_title()))))+ (", ");
		fieldUpdatedList += ("agreement_title") + (", ");
	}
	if (!foundContract.getAgreement_type().equals(contract.getAgreement_type())) {
		fieldBeforeList += ((String.valueOf((foundContract.getAgreement_type()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getAgreement_type()))))+ (", ");
		fieldUpdatedList += ("agreement_type") + (", ");
	}
	if (!foundContract.getDescription().equals(contract.getDescription())) {
		fieldBeforeList += ((String.valueOf((foundContract.getDescription()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getDescription()))))+ (", ");
		fieldUpdatedList += ("description") + (", ");
	}
	if (!foundContract.getAgreement_location().equals(contract.getAgreement_location())) {
		fieldBeforeList += ((String.valueOf((foundContract.getAgreement_location()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getAgreement_location()))))+ (", ");
		fieldUpdatedList += ("agreement_location") + (", ");
	}
	if (!foundContract.getLanguage().equals(contract.getLanguage())) {
		fieldBeforeList += ((String.valueOf((foundContract.getLanguage()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getLanguage()))))+ (", ");
		fieldUpdatedList += ("language") + (", ");
	}
	if (!foundContract.getRegion().equals(contract.getRegion())) {
		fieldBeforeList += ((String.valueOf((foundContract.getRegion()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getRegion()))))+ (", ");
		fieldUpdatedList += ("region") + (", ");
	}
	if (!foundContract.getRelated_agreements().equals(contract.getRelated_agreements())) {
		fieldBeforeList += ((String.valueOf((foundContract.getRelated_agreements()))))+ (", ");
		fieldAfterList += ((String.valueOf((contract.getRelated_agreements()))))+ (", ");
		fieldUpdatedList += ("related_agreements") + (", ");
	}
		Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		contract.setDate_updated(timeNow);
		blank.setField_after(fieldAfterList);
		blank.setField_before(fieldBeforeList);
		blank.setField_updated(fieldUpdatedList);
		blank.setUserid(contract.getUser());
		blank.setRequestedid(contract);
		blank.setDate(contract.getDate_updated());
		contractService.update(contract);
		auditService.addAudit(blank);
		return "redirect:/update_status/" + contract.getRequestid();
	}
	
	@GetMapping("/update_status/{requestid}")
	public String updateStatus(@PathVariable("requestid") int requestid, Model model) {
		negRepo.findById(requestid).ifPresent(in_negotiation->model.addAttribute("in_negotiation", in_negotiation));
		opRepo.findById(requestid).ifPresent(operative->model.addAttribute("operative", operative));
		exRepo.findById(requestid).ifPresent(expired->model.addAttribute("expired", expired));
		return "update_status";	
	}
	
	@PostMapping("/api/update/in_negotiation")
	public String updateInNegotiation(@ModelAttribute(name="in_negotiation") InNegotiation in_negotiation) {
		in_negotiationService.update(in_negotiation);
		Integer requestid = in_negotiation.getRequestId();
		StatusLink stat = new StatusLink(requestid, "in_negotiation");
		statuslinkService.update(stat);
		return "redirect:/";
	}
	
	@PostMapping("/api/update/operative")
	public String updateOperative(@ModelAttribute(name="operative") Operative operative) {
		operativeService.update(operative);
		Integer requestid = operative.getRequestId();
		StatusLink stat = new StatusLink(requestid, "operative");
		statuslinkService.update(stat);
		return "redirect:/search_contracts";
	}
	
	@PostMapping("/api/update/expired")
	public String updateExpired(@ModelAttribute(name="expired") Expired expired) {
		expiredService.update(expired);
		Integer requestid = expired.getRequestid();
		StatusLink stat = new StatusLink(requestid, "expired");
		statuslinkService.update(stat);
		return "redirect:/search_contracts";
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
		Favourited favourite = new Favourited();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		favourite.setUser(user);
		favourite.setContract(favouritedContract);
		favouritedService.addFavourite(favourite);
		return "redirect:/favourite_contracts";
	}

	@GetMapping("/favourite_contracts")
	public String getFavouritedContracts(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		model.addAttribute("contracts", contractService.getFavouritedContracts(user.getUserid()));
		model.addAttribute("currentuser", user.getUsername());
		return "favourite_contracts";
	}
	
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
	
	@GetMapping("/help")
	public String help() {
		return "/help";
	}
	
	@GetMapping("/admin_settings")
	public String adminSettings() {
		return "/admin_settings";
	}
	

}
	
