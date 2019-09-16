package contracts.controller;

import java.text.ParseException;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import contracts.domain.User;
import contracts.service.IAccountService;

@Controller
public class AccountController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAccountService accountService;

	
	@GetMapping("/create_account")
    public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
        return "create_account";
    }
	
	@PostMapping("/api/staff")
	public String addUser(@Valid @ModelAttribute(name="user") User user, BindingResult br, Model model) {
		if(br.hasErrors()) {
			return "create_account";
		}
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			if (accountService.userExists(user.getUsername())==true) {
				Random r = new Random();
			    String randomNumber = String.format("%04d", r.nextInt(1000));
			    String randomNumber2 = String.format("%04d", r.nextInt(1000));
			throw new IllegalArgumentException("Username is already in use. Please enter another username. "
					+ "Suggestions: " + user.getUsername() + randomNumber + (", ") + user.getUsername() + randomNumber2);
		}
		}
		catch (IllegalArgumentException e){
			model.addAttribute("message", e.getMessage());
			return "create_account";
		}
		accountService.addAccount(user);
		return "index";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
	    return "login";
	}

}
