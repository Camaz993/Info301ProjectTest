package contracts.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


import contracts.domain.Contract;
import contracts.domain.Status;
import contracts.domain.User;
import contracts.service.IContractService;

@Controller
public class ContractController {
	
	@Autowired
	private IContractService contractService;
	
	@GetMapping("/add_contracts")
    public String showSignUpForm(Model model) {
		model.addAttribute("contract", new Contract());
        return "add_contracts";
    }
	
	@PostMapping("/api/contracts")
	public ResponseEntity<String> addContract(@RequestParam Integer user, @RequestParam String agreement_title, @RequestParam String agreement_type,
			@RequestParam String description, @RequestParam String agreement_location, @RequestParam String language, @RequestParam String region, @RequestParam String related_agreements)
	{
		Contract contract = new Contract();
		User userFind = contractService.findById(user).orElse(new User());
		contract.setUserid(userFind);
		contract.setAgreement_title(agreement_title);
		contract.setAgreement_type(agreement_type);
		contract.setDescription(description);
		contract.setAgreement_location(agreement_location);
		contract.setLanguage(language);
		contract.setRegion(region);
		contract.setRelated_agreements(related_agreements);
		
		contractService.addContract(contract);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/");
		return new ResponseEntity<String>(headers, HttpStatus.MOVED_PERMANENTLY);
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
	public ResponseEntity<String> updateDetails(@PathVariable(name = "requestid") Long requestid, @RequestParam User user, @RequestParam List<Status> statusList, @RequestParam String agreement_title, @RequestParam String agreement_type,
			@RequestParam String description, @RequestParam String agreement_location, @RequestParam String language, @RequestParam String region, @RequestParam String related_agreements) {
		
		contractService.updateDetails(requestid, user, statusList, agreement_title, agreement_type, description, agreement_location, language, region, related_agreements);
 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
