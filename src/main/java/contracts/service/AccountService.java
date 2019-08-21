package contracts.service;

import java.util.List;

import contracts.domain.user;
import contracts.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public void addAccount(user newAccount) {
		
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
}
