package contracts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import contracts.service.FavouritedService;

@Controller
public class FavouritedController {
	
	@SuppressWarnings("unused")
	@Autowired
	private FavouritedService favouritedService;
	
	

}
