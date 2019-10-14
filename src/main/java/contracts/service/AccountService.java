/** 
 * The account service class for accessing the account table in the database
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.User;
import contracts.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountRepository accountRepository;
	
	private String passwordRegex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

	@Override
	public void addAccount(User newAccount) {
		try
		{
			accountRepository.save(newAccount);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	@Override
	public boolean userExists(String username) {
		if (accountRepository.findByUsername(username) == null) {
			return false;	
	}
		return true;
	}
	
	@Override
	public boolean validate(String password){	  
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(passwordRegex);
		matcher = pattern.matcher(password);
		return matcher.matches();    	    
	  }
	
	@Override
	public User findUser(String username) {
		return accountRepository.findByUsername(username);
	}
	
	@Override
	public List<String> getUserRoles() {
		return accountRepository.getUserRoles();
	}
	
	@Override
	public void update(User user) {	
		accountRepository.save(user);
	}
	
	
}
