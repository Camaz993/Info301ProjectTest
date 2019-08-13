package main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {
	
	private final ContractRepository repository;
	
	ContractController(ContractRepository repository){
		this.repository=repository;
	}
	

	@PostMapping("/contracts")
	contract newContract(@RequestBody contract newContract) {
		return repository.save(newContract);
	}

}
