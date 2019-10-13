package contracts.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import contracts.domain.Contract;
import contracts.domain.User;
import contracts.repository.CurrentRepository;
import contracts.service.CurrentService;
import contracts.service.EmailService;
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
	
	@Autowired
	private EmailService emailService;
	
	private User newUser;
	
	@Autowired
	private CurrentService currentService;
	
	@Autowired
	private CurrentRepository currentRepository;
	
	@GetMapping("/create_account")
    public String showSignUpForm(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("user", new User());
		model.addAttribute("roles", accountService.getUserRoles());
        return "create_account";
    }
	
	@PostMapping("/api/staff")
	public String addUser(@Valid @ModelAttribute(name="user") User user, BindingResult br, Model model, RedirectAttributes redirectAttributes) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
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
				throw new IllegalArgumentException("Password must be between 6 and 20 characters, contain 1 digit, 1 lowercase letter, 1 uppercase letter and 1 special symbol @#$%");
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
		redirectAttributes.addFlashAttribute("message4", "Account successfully created");
		return "redirect:/create_account";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
	    return "login";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/manage_users")
	public String manageUsers(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		List <User> users = contractService.getAllUsers();
		model.addAttribute("users", users);
	    return "manage_users";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/lock_users/{userid}")
	public String selectedUserLock(@PathVariable("userid") int userid, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		User foundUser = contractService.findById(userid).orElse(new User());
		foundUser.setLocked(true);
		accountService.addAccount(foundUser);
		return "redirect:/manage_users";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/unlock_users/{userid}")
	public String selectedUserUnlock(@PathVariable("userid") int userid, Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		User foundUser = contractService.findById(userid).orElse(new User());
		foundUser.setLocked(false);
		accountService.addAccount(foundUser);
		return "redirect:/manage_users";
	}
	
	@GetMapping("/forgot_password")
	public String getForgotPassword() {
		return "forgot_password";
	}
	
	@RequestMapping("/forgotpassword")
	public String forgotPassword(ModelMap map, Model model, @RequestParam String username, RedirectAttributes redirectAttributes) {
		map.put("username",username);
		newUser = accountService.findUser(username);
		try {
		if (newUser == null) {
			throw new UsernameNotFoundException("Error: Unable to locate your details!");
		}
		}
			catch (UsernameNotFoundException e){
				model.addAttribute("message", e.getMessage());
				return "forgot_password";
			}
		try {
			if (newUser.getEmail() == null) {
				throw new IllegalArgumentException("Error: No email associated with that account! Contact your admin team!");
			}
			}
				catch (IllegalArgumentException e){
					model.addAttribute("message", e.getMessage());
					return "forgot_password";
				}
		String token = UUID.randomUUID().toString();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date expiryDate = new Date(System.currentTimeMillis()+3*60*1000);
		emailService.send(newUser.getEmail(), "Password Recovery: Contract Management System", 
				"Hi there,\n Here is the password recovery token. If you have not requested one please contact your admin"
				+ "team ASAP. Otherwise, log into your account with this token:\n"
				+ token + "\nFrom the Contract Management Team.");
		String pass = newUser.getPassword();
		newUser.setExpiryDate(expiryDate);
		newUser.setPassword(passwordEncoder.encode(token));
		newUser.setPassrepeat(passwordEncoder.encode(token));
		accountService.update(newUser);
		redirectAttributes.addFlashAttribute("message2", "Email successfully sent");
		return "redirect:/forgot_password";	
	}
	
	@GetMapping("/change_password")
	public String showPasswordForm(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("firstname", user.getFirstname());
		model.addAttribute("lastname", user.getLastname());
		model.addAttribute("email", user.getEmail());
		return "change_password";
	}
	
	@PostMapping("/update_password")
	public String updatePassword(ModelMap map, Model model, @RequestParam String passwordChange, @RequestParam String passwordChangeRepeat, RedirectAttributes redirectAttributes){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		map.put("passwordChange", passwordChange);
		map.put("passwordChangeRepeat", passwordChangeRepeat);
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		try {	
			if (!(passwordChange.equals(passwordChangeRepeat))) {
				throw new IllegalArgumentException("Password must match password repeat");
			}
		}
		catch (IllegalArgumentException e){
			model.addAttribute("message", e.getMessage());
			model.addAttribute("message4", "There were errors in your password submission!");
			return "change_password";
		}
		try {	
			if (accountService.validate(passwordChange)==false) {
				throw new IllegalArgumentException("Password must be between 6-20 characters, contain 1 digit, 1 lowercase letter, 1 uppercase letter");
			}
		}
		catch (IllegalArgumentException e){
			model.addAttribute("message3", e.getMessage());
			model.addAttribute("message4", "There were errors in your password submission!");
			return "change_password";
		}
		user.setPassword(passwordEncoder.encode(passwordChange));
		user.setPassrepeat(passwordEncoder.encode(passwordChangeRepeat));
		user.setExpiryDate(null);
		accountService.update(user);
		redirectAttributes.addFlashAttribute("message2", "Password successfully changed!");
		return "redirect:/change_password";
	}
	
	@PostMapping("/update_email")
	public String updateEmail(ModelMap map, @RequestParam String updateEmail, RedirectAttributes redirectAttributes){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		map.put("updateEmail", updateEmail);
		user.setEmail(updateEmail);
		accountService.update(user);
		redirectAttributes.addFlashAttribute("message2", "Email successfully updated!");
		return "redirect:/change_password";
	}
	
	
	

}
