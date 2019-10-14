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
	
	private String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!"
			+ "#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d"
			+ "-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
			+ "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
			+ "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\"
			+ "x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
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
	public boolean validateEmail(String email){	  
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(emailRegex);
		matcher = pattern.matcher(email);
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
