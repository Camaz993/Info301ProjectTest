package main.controller;

import java.util.List;
import main.domain.contract;
import 

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {
	
	@Autowired
	private IContractService contractService;
	
	@PostMapping("/api/contracts")
	public ResponseEntity<String> addContract(@RequestParam User user, @RequestParam List<Status> statusList, @RequestParam String agreement_title, @RequestParam String agreement_type,
			@RequestParam String description, @RequestParam String agreement_location, @RequestParam String language, @RequestParam String region, @RequestParam String related_agreements)
	{
		Contract contract = new contract();
		contract.setUser(user);
		contract.setStatusList(statusList);
		contract.setAgreementTitle(agreement_title);
		contract.setAgreementType(agreement_type);
		contract.setDescription(description);
		contract.setAgreementLocation(agreement_location);
		contract.setLanguage(language);
		contract.setRegion(region);
		contract.setRelatedAgreements(related_agreements);
		
		contractService.addContract(contract);
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/api/allcontracts")
	public List<contract> getAllContracts() {
		return contractService.getAllContracts;
	}

}
