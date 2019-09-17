package contracts.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
		contractService.addContract(contract);
		return "index";
	}
	
	@GetMapping("/search_contracts")
	public String getAllContracts(Model model) {
		model.addAttribute("contracts", contractService.getAllContracts());
		return "search_contracts";
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
	
	@PutMapping("/api/contracts/{requestid}") 
	public ResponseEntity<String> updateDetails(@PathVariable(name = "requestid") Integer requestid, @RequestParam User user, @RequestParam List<Status> statusList, @RequestParam String agreement_title, @RequestParam String agreement_type,
			@RequestParam String description, @RequestParam String agreement_location, @RequestParam String language, @RequestParam String region, @RequestParam String related_agreements) {
		
		contractService.updateDetails(requestid, user, statusList, agreement_title, agreement_type, description, agreement_location, language, region, related_agreements);
 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
	
	@PutMapping("/archive_contracts")
	public String archiveContract(Contract contract) {
		
		contract.setArchived("T");
		contractService.archiveContract(contract);
		
		return "archive_contracts";
	}
}
	
