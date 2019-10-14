/** 
** The security config set up
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import contracts.service.IAuthenticationFacade;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        	.antMatchers("/forgotpassword").permitAll()
        	.antMatchers("/forgot_password").permitAll()
        	.anyRequest().authenticated()
        	.and()
        .formLogin()
            .loginPage("/login")
        	.defaultSuccessUrl("/", true)
        	.permitAll()
            .usernameParameter("username").passwordParameter("password")
            .and()
        .logout()                                    
            .permitAll();
    }
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}

