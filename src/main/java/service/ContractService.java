package service;

import java.util.List;

import org.springframework.stereotype.Service;

import domain.contract;
import repository.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ContractService {
	
	@Autowired
	ContractRepository contractRepository;
	
	//@Override
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
	
	//@Override
	public List<contract> getAllContracts() {
		return contractRepository.findAll();
	}

}
