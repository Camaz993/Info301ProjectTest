package contracts.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
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
	
	@GetMapping("/audit")
	public String getAllContracts(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("audits", auditService.getAudit());
		return "audit";
	}

	

}
