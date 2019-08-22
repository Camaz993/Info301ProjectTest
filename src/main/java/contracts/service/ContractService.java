package contracts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import contracts.domain.contract;
import contracts.repository.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContractService implements IContractService{
	
	@Autowired
	ContractRepository contractRepository;
	
	@Override
	public void addContract(contract newContract) {
		
		try
		{
			contractRepository.save(newContract);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
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
