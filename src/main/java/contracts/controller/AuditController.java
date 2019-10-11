package contracts.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.Expired;
import contracts.domain.InNegotiation;
import contracts.domain.Operative;
import contracts.domain.Status;
import contracts.domain.User;
import contracts.repository.CurrentRepository;
import contracts.service.IAccountService;
import contracts.service.IAuditService;
import contracts.service.IContractService;
import contracts.service.ICurrentService;

@Controller
public class AuditController {
	
	@Autowired
	private IAuditService auditService;
	
	@Autowired
	private ICurrentService currentService;
	
	@Autowired
	private CurrentRepository currentRepository;
	
	@Autowired
	private IContractService contractService;
	
	@Autowired
	private IAccountService accountService;
	
	//See stack overflow: 
	//https://stackoverflow.com/questions/11271554/compare-two-objects-in-java-with-possible-null-values/11271611
	public static boolean compare(String str1, String str2) {
	    return (str1 == null ? str2 == null : str1.equals(str2));
	}
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = accountService.findUser(username);
		return user;
	}
	
	@GetMapping("/audit")
	public String getAllContracts(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("audits", auditService.getAudit());
		return "audit";
	}
	
	//update a contract and store the results of the changes in the audit table
		@Secured({ "ROLE_ADMIN", "ROLE_LEGAL" })
		@PostMapping("/api/updates")
		public String updateDetails(@Valid @ModelAttribute(name="contract") Contract contract, BindingResult br, Model model)
		{	
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		String fieldUpdatedList = "";
		String fieldBeforeList = "";
		String fieldAfterList = "";
		Contract foundContract = contractService.findContract(contract.getRequestid()).orElse(new Contract());
		Audit blank = new Audit();
		if (!compare(String.valueOf(foundContract.getUser()),String.valueOf(contract.getUser()))) {
			if (foundContract.getUser() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getUser().getUserid()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getUser() != null) {
				fieldAfterList += ((String.valueOf((contract.getUser().getUserid()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("userid") + (", ");
		}
		if (!compare(foundContract.getAgreement_title(),contract.getAgreement_title())) {
			if (foundContract.getAgreement_title() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getAgreement_title()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getAgreement_title() != null) {
				fieldAfterList += ((String.valueOf((contract.getAgreement_title()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			
			fieldUpdatedList += ("agreement_title") + (", ");
		}
		if (!compare(foundContract.getAgreement_type(),contract.getAgreement_type())) {
			if (foundContract.getAgreement_type() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getAgreement_type()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getAgreement_type() != null) {
				fieldAfterList += ((String.valueOf((contract.getAgreement_type()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("agreement_type") + (", ");
		}
		if (!compare(foundContract.getDescription(),contract.getDescription())) {
			if (foundContract.getDescription() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getDescription()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getDescription() != null) {
				fieldAfterList += ((String.valueOf((contract.getDescription()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("description") + (", ");
		}
		if (!compare(foundContract.getAgreement_location(),contract.getAgreement_location())) {
			if (foundContract.getAgreement_location() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getAgreement_location()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getAgreement_location() != null) {
				fieldAfterList += ((String.valueOf((contract.getAgreement_location()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}	
			fieldUpdatedList += ("agreement_location") + (", ");
		}
		if (!compare(foundContract.getBusinessname(),contract.getBusinessname())) {
			if (foundContract.getBusinessname() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getBusinessname()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getBusinessname() != null) {
				fieldAfterList += ((String.valueOf((contract.getBusinessname()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("businessname") + (", ");
		}
		if (!compare(foundContract.getClientname(),contract.getClientname())) {
			if (foundContract.getClientname() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getClientname()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getClientname() != null) {
				fieldAfterList += ((String.valueOf((contract.getClientname()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("clientname") + (", ");
		}
		if (!compare(foundContract.getAddress(),contract.getAddress())) {
			if (foundContract.getAddress() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getAddress()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getAddress() != null) {
				fieldAfterList += ((String.valueOf((contract.getAddress()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("address") + (", ");
		}
		if (!compare(foundContract.getPhone(),contract.getPhone())) {
			if (foundContract.getPhone() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getPhone()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getPhone() != null) {
				fieldAfterList += ((String.valueOf((contract.getPhone()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}	
			fieldUpdatedList += ("phone") + (", ");
		}
		if (!compare(foundContract.getEmail(),contract.getEmail())) {
			if (foundContract.getEmail() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getEmail()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getEmail() != null) {
				fieldAfterList += ((String.valueOf((contract.getEmail()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("email") + (", ");
		}
		if (!compare(foundContract.getFax(),contract.getFax())) {
			if (foundContract.getFax() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getFax()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getFax() != null) {
				fieldAfterList += ((String.valueOf((contract.getFax()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			
			fieldUpdatedList += ("fax") + (", ");
		}
		if(!compare(foundContract.getLanguage(),contract.getLanguage())) {
			if (foundContract.getLanguage() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getLanguage()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getLanguage() != null) {
				fieldAfterList += ((String.valueOf((contract.getLanguage()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}	
			fieldUpdatedList += ("language") + (", ");
		}
		if (!compare(foundContract.getRegion(),contract.getRegion())) {
			if (foundContract.getRegion() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getRegion()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getRegion() != null) {
				fieldAfterList += ((String.valueOf((contract.getRegion()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("region") + (", ");
		}
		if (!compare(foundContract.getRelated_agreements(), contract.getRelated_agreements())) {
			if (foundContract.getRelated_agreements() != null) {
				fieldBeforeList += ((String.valueOf((foundContract.getRelated_agreements()))))+ (", ");
			}
			else {
				fieldBeforeList += (" ")+ (", ");
			}
			if (contract.getRelated_agreements() != null) {
				fieldAfterList += ((String.valueOf((contract.getRelated_agreements()))))+ (", ");
			}
			else {
				fieldAfterList += (" ")+ (", ");
			}
			fieldUpdatedList += ("related_agreements") + (", ");
		}
			Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
			contract.setDate_updated(timeNow);
			blank.setField_after(fieldAfterList);
			blank.setField_before(fieldBeforeList);
			blank.setField_updated(fieldUpdatedList);
			blank.setUserid(getCurrentUser());
			blank.setRequestedid(contract);
			blank.setDate(contract.getDate_updated());
			contractService.update(contract);
			auditService.addAudit(blank);
			return "redirect:/update_status/" + contract.getRequestid();
		}

	

}
