package contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import contracts.domain.User;
import contracts.domain.CurrentUser;
import contracts.repository.AccountRepository;

@Service
public class ContractUserDetailsService implements UserDetailsService {

@Autowired
private AccountRepository userRepository;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	User user = userRepository.findByUsername(username);
	if(user==null) {
		throw new UsernameNotFoundException("User not found!");
	}
	
	return new CurrentUser(user);

}
}
