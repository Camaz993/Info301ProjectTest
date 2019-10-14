/** 
 ** The current controller contains the get, post and request mappings for all of the css html pages
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
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
import contracts.repository.CurrentRepository;
import contracts.service.ICurrentService;

@Controller
public class CurrentController {
	
	@Autowired
	private ICurrentService currentService;
	
	@Autowired
	private CurrentRepository currentRepository;

	/**
	 * adds the current css to the admin settings page
	 */
	@GetMapping("/admin_settings")
    public String showAdmin(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("current", new Current());
        return "admin_settings";
    }
	
	/**
	 * updates the css to the blue theme
	 */
	@PostMapping("/api_adminblue")
	public String changeColourBlue(@ModelAttribute(name="current") Current current) {
		current.setColour("style");
		currentService.addCurrent(current);
		return "redirect:/admin_settings";
	}

	/**
	 * updates the css to the green theme
	 */
	@PostMapping("/api_admingreen")
	public String changeColourGreen(@ModelAttribute(name="current") Current current) {
		current.setColour("greenstyle");
		currentService.addCurrent(current);
		return "redirect:/admin_settings";
	}
	
	/**
	 * updates the css to the grey theme
	 */
	@PostMapping("/api_admingrey")
	public String changeColourGrey(@ModelAttribute(name="current") Current current) {
		current.setColour("greystyle");
		currentService.addCurrent(current);
		return "redirect:/admin_settings";
	}
	
	/**
	 * updates the css to the red theme
	 */
	@PostMapping("/api_adminred")
	public String changeColourRed(@ModelAttribute(name="current") Current current) {
		current.setColour("redstyle");
		currentService.addCurrent(current);
		return "redirect:/admin_settings";
	}

}
