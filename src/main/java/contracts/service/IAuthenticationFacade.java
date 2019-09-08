package contracts.service;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	
	    Authentication getAuthentication();

}
