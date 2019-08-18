package src.main.services;

import java.util.List;

import src.main.domain.contract;
import src.main.repositories.ContractRepository;

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
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	@Override
	public List<contract> getAllContracts() {
		return contractRepository.findAll();
	}

}
