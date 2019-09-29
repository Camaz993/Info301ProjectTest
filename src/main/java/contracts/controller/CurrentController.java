package contracts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import contracts.domain.Contract;
import contracts.domain.Current;
import contracts.service.ICurrentService;

@Controller
public class CurrentController {
	
	@Autowired
	private ICurrentService currentService;
	
	@GetMapping("/admin_settings")
    public String showAdmin(Model model) {
		model.addAttribute("currents", currentService.getAllCurrent());
        return "admin_settings";
    }
	
	@PostMapping("/api_adminblue")
	public String changeColourBlue() {
		currentService.updateBackground(2, "blue");
		return "redirect:/admin_settings";
	}

}
