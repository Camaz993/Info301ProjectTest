package main.services;

import main.domain.contract;

public class ContractService {
	
	ContractRepository contractRepository;
	
	@Override
	public void addContract(contract newContract) {
		
		try
		{
			contractRepository.save(newContract);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getmessage());
		}catch (Excpetion e2)
		{
			e2.printStackTrace();
		}
		
	}

}
