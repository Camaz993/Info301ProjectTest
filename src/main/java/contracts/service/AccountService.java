package contracts.service;

import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import contracts.domain.User;
import contracts.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountRepository accountRepository;

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
	
}
