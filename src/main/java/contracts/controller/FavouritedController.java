package contracts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import contracts.service.FavouritedService;

@Controller
public class FavouritedController {
	
	@Autowired
	private FavouritedService favouritedService;
	
	

}
