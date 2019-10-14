/** 
** The currentuser domain class includes all of the columns that match the database along with the getter and setter methods, and the to string method
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.domain;


import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import contracts.security.SecurityConfig;

public class CurrentUser implements UserDetails {
	
	@Bean
	public PasswordEncoder passwordEncoder2() {
	    return new BCryptPasswordEncoder();
	}

	private User user;
	
	public CurrentUser(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if (user.isLocked() == false) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		if (user.getExpiryDate() != null) {
			if (user.getExpiryDate().compareTo(timeNow) < 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
