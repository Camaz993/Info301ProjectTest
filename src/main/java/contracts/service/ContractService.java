package contracts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import contracts.domain.Contract;
import contracts.domain.Status;
import contracts.domain.User;
import contracts.repository.AccountRepository;
import contracts.repository.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContractService implements IContractService{
	
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public void setContractRepository(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
	}
	
	@Override
	public void addContract(Contract newContract) {
		
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
	public List<Contract> getAllContracts() {
		return contractRepository.findAll();
	}
	
	@Override
	public List<User> getAllUsers() {
		return accountRepository.findAll();
	}
	
	@Override
    public Optional<User> findById(Integer id) {
        return accountRepository.findById(id);
    }
	
	@Override
	public List<Contract> searchContracts(String search) {
		return contractRepository.searchContracts(search);
	}
	
	@Override
	public List<Contract> searchLocation(String search) {
		return contractRepository.searchLocation(search);
	}
	
	@Override
	public List<Contract> searchContractType(String search) {
		return contractRepository.searchContractType(search);
	}
	
	@Override
	public void update(Integer requestid) {
		
		Contract contract = contractRepository.findById(requestid).orElse(new Contract());
		contract.setAgreement_title(contract.getAgreement_title());
		contract.setAgreement_type(contract.getAgreement_type());
		contract.setDescription(contract.getDescription());
		contract.setAgreement_location(contract.getAgreement_location());
		contract.setLanguage(language);
		contract.setRegion(region);
		contract.setRelated_agreements(related_agreements);
		contractRepository.save(contract);
	}

}
