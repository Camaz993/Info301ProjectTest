/**
 * The css controller contains the get, post and request mappings for all of the css html pages
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import contracts.domain.Current;
import contracts.domain.CurrentCss;
import contracts.repository.CurrentRepository;
import contracts.service.ICurrentCssService;
import contracts.service.ICurrentService;

@Controller
public class CssController {
	
	@Autowired
	private ICurrentCssService cssService;
	
	@Autowired
	private ICurrentService currentService;
	
	@Autowired
	private CurrentRepository currentRepository;
	
	/**
	 * adds the css to the site settings page
	 */
	@GetMapping("/site_settings")
	public String getAllContracts(Model model) {
		Integer i = currentService.getCurrent();
		currentRepository.findById(i).ifPresent(current->model.addAttribute("currentCss", current));
		model.addAttribute("current", new Current());
		return "site_settings";
	}
	
	/**
	 * Updates the current css
	 */
	@PostMapping("/api/css")
	public String addCss(@ModelAttribute(name="currentCss") CurrentCss css) {
		cssService.addCurrentCss(css);
		return "redirect:/site_settings";
	}
	
	

}
