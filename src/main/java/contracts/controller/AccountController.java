package contracts.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import contracts.domain.User;
import contracts.service.IAccountService;
import contracts.service.IContractService;

@Controller
public class AccountController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAccountService accountService;

	@Autowired
	private IContractService contractService;
	
	@GetMapping("/create_account")
    public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", accountService.getUserRoles());
        return "create_account";
    }
	
	//adds a new user account and sets their permissions 
	@PostMapping("/api/staff")
	public String addUser(@Valid @ModelAttribute(name="user") User user, BindingResult br, Model model, RedirectAttributes redirectAttributes) {
		if(br.hasErrors()) {
			return "create_account";
		}
		try {
			if (accountService.userExists(user.getUsername())==true) {
				Random r = new Random();
			    String randomNumber = String.format("%04d", r.nextInt(1000));
			    String randomNumber2 = String.format("%04d", r.nextInt(1000));
			throw new IllegalArgumentException("Username is already in use. Please enter another username. "
					+ "Suggestions: " + user.getUsername() + randomNumber + (", ") + user.getUsername() + randomNumber2);
		}
		}
		catch (IllegalArgumentException e){
			model.addAttribute("message1", e.getMessage());
			return "create_account";
		}
		try {	
			if (!(user.getPassword().equals(user.getPassrepeat()))) {
				throw new IllegalArgumentException("Password must match password repeat");
			}
		}
		catch (IllegalArgumentException e){
			model.addAttribute("message2", e.getMessage());
			return "create_account";
		}
		try {	
			if (accountService.validate(user.getPassword())==false) {
				throw new IllegalArgumentException("Password must be between 6 and 20 characters, contain at least 1 digit, 1 lowercase letter and 1 uppercase letter");
			}
		}
		catch (IllegalArgumentException e){
			model.addAttribute("message3", e.getMessage());
			return "create_account";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPassrepeat(passwordEncoder.encode(user.getPassrepeat()));
		user.setLocked(false);
		accountService.addAccount(user);
		redirectAttributes.addFlashAttribute("message", "User successfully added");
		return "redirect:/create_account";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
	    return "login";
	}
	
	@GetMapping("/manage_users")
	public String manageUsers(Model model) {
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
	    return "manage_users";
	}
	
	//locks a user account so the user can no longer log in
	@GetMapping("/lock_users/{userid}")
	public String selectedUserLock(@PathVariable("userid") int userid, Model model) {
		User foundUser = contractService.findById(userid).orElse(new User());
		foundUser.setLocked(true);
		accountService.addAccount(foundUser);
		return "redirect:/manage_users";
	}
	
	//unlocks a user account so they can log in 
	@GetMapping("/unlock_users/{userid}")
	public String selectedUserUnlock(@PathVariable("userid") int userid, Model model) {
		User foundUser = contractService.findById(userid).orElse(new User());
		foundUser.setLocked(false);
		accountService.addAccount(foundUser);
		return "redirect:/manage_users";
	}
	
	
	

}
