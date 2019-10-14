/** 
 * The authenticationfacade interface for the service class
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	
	    Authentication getAuthentication();

}
