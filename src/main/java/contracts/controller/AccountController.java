package contracts.controller;

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
	public String addUser(@Valid @ModelAttribute(name="user") User user, BindingResult br) {
		if(br.hasErrors()) {
			return "create_account";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		accountService.addAccount(user);
		return "index";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
	    return "login";
	}

}
