package contracts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import contracts.domain.user;
import contracts.service.IAccountService;
import contracts.service.AccountService;
import contracts.repository.AccountRepository;

@RestController
public class AccountController {
	
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/api/staff")
	public ResponseEntity<String> createAccount(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String role, 
			@RequestParam String username, @RequestParam String password, @RequestParam boolean locked)
	
	{
		user account = new user();
		account.setFirst_name(first_name);
		account.setLast_name(last_name);
		account.setRole(role);
		account.setUsername(username);
		account.setPassowrd(password);
		account.setLocked(locked);
		
		accountService.addAccount(account);
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
		
	}

}
