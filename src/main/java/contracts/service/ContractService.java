package contracts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import contracts.domain.contract;
import contracts.domain.user;
import contracts.repository.AccountRepository;
import contracts.repository.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContractService implements IContractService{
	
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
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
	
	@Override
    public Optional<user> findById(Integer id) {
        return accountRepository.findById(id);
    }
	
	@Override
	public List<contract> searchContracts(String search) {
		return contractRepository.searchContracts(search);
	}
	
	@Override
	public List<contract> searchLocation(String search) {
		return contractRepository.searchLocation(search);
	}
	
	@Override
	public List<contract> searchContractType(String search) {
		return contractRepository.searchContractType(search);
	}

}
