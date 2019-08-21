package contracts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import contracts.domain.contract;
import contracts.domain.status;
import contracts.domain.user;
import contracts.service.IContractService;

@RestController
public class ContractController {
	
	@Autowired
	private IContractService contractService;
	
	@PostMapping("/api/contracts")
	public ResponseEntity<String> addContract(@RequestParam user user, @RequestParam List<status> statusList, @RequestParam String agreement_title, @RequestParam String agreement_type,
			@RequestParam String description, @RequestParam String agreement_location, @RequestParam String language, @RequestParam String region, @RequestParam String related_agreements)
	{
		contract contract = new contract();
		contract.setUserid(user);
		contract.setStatusid(statusList);
		contract.setAgreement_title(agreement_title);
		contract.setAgreement_type(agreement_type);
		contract.setDescription(description);
		contract.setAgreement_location(agreement_location);
		contract.setLanguage(language);
		contract.setRegion(region);
		contract.setRelated_agreements(related_agreements);
		
		contractService.addContract(contract);
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/api/allcontracts")
	public List<contract> getAllContracts() {
		return contractService.getAllContracts();
	}

}
