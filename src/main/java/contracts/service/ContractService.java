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
		return contractRepository.getCurrentContracts();
	}
	
	@Override
    public Optional<Contract> findContract(Integer id) {
        return contractRepository.findById(id);
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
	public List<Contract> findAllByOrderByIdAsc(){
		return contractRepository.findAllByOrderByIdAsc();
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
	public Contract update(Contract contract) {	
		return contractRepository.save(contract);
	}
	
	@Override
	public void archiveContract(Contract archivedContract) {
		contractRepository.archiveContract(archivedContract);
		
	}
	
	@Override
	public List<Contract> getArchivedContracts() {
		return contractRepository.getArchivedContracts();
	}
	
	@Override
	public void unarchiveContract(Contract unarchiveContract) {
		contractRepository.unarchiveContract(unarchiveContract);
	}

	@Override
	public List<Contract> getUnarchivedContracts() {
		return contractRepository.getCurrentContracts();
	}

}
