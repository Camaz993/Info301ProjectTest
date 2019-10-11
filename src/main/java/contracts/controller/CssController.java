package contracts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import contracts.domain.Current;
import contracts.domain.CurrentCss;
import contracts.service.ICurrentCssService;

@Controller
public class CssController {
	
	@Autowired
	private ICurrentCssService cssService;
	
	@GetMapping("/site_settings")
	public String getAllContracts(Model model) {
		Integer id = cssService.getCurrentCss();
		model.addAttribute("currentCss", cssService.findCss(id));
		model.addAttribute("current", new Current());
		return "site_settings";
	}
	
	@PostMapping("/api/css")
	public String addCss(@ModelAttribute(name="currentCss") CurrentCss css) {
		cssService.addCurrentCss(css);
		return "redirect:/site_settings";
	}
	
	

}
