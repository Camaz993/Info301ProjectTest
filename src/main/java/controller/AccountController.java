package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.user;
import service.IAccountService;

@RestController
public class AccountController {
	
	@PostMapping("/api/staff")
	public ResponseEntity<String> createAccount(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String role, 
			@RequestParam String username, @RequestParam String password, @RequestParam boolean locked)
	
	{
		Account account = new Account();
		account.setFirstName(first_name);
		account.setLastName(last_name);
		account.setRole(role);
		account.setUsername(username);
		account.setPassword(password);
		account.setLocked(locked);
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
		
	}
	
	
	this.first_name = first_name;
	this.last_name = last_name;
	this.role = role;
	this.username = username;
	this.password = password;
	this.locked = locked;

}
