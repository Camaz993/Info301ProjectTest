package contracts.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.InNegotiation;
import contracts.domain.Operative;
import contracts.repository.AccountRepository;
import contracts.repository.OperativeRepository;


@Service
public class OperativeService implements IOperativeService{
	
	@Autowired
	OperativeRepository operativeRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public void setOperativeRepository(OperativeRepository operativeRepository) {
		this.operativeRepository = operativeRepository;
	}
	
	@Override
	public void addOperative(Operative newOperative) {
		
		try
		{
			operativeRepository.save(newOperative);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	@Override
	public Operative update(Operative newOperative) {	
		return operativeRepository.save(newOperative);
	}
	
	
}
