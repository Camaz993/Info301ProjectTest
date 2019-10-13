package contracts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import contracts.domain.Contract;
import contracts.domain.Favourited;
import contracts.domain.StatusLink;
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
	public List<Contract> getContractsShortList() {
		return contractRepository.getContractsShortList();
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
	public List<Contract> getContractsSorted() {
		return contractRepository.getContractsSorted();
	}
	
	@Override
	public List<Contract> getContractsSortedParty() {
		return contractRepository.getContractsSortedParty();
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
	public List<Contract> getUnarchivedContracts() {
		return contractRepository.getCurrentContracts();
	}
	
	@Override
	public List<Contract> getContractsByUser(Integer userid) {
		return contractRepository.getContractsByUser(userid);
	}
	
	@Override
	public List<Contract> getNullUserContracts(){
		return contractRepository.getNullUserContracts();
	}
	
	@Override
	public List<Contract> getFavouritedContracts(Integer userid) {
		return contractRepository.getFavouritedContracts(userid);
	}
	
	@Override
	public Integer findNewestContract() {
		return contractRepository.findNewestContract();
	}
	
	@Override
	public void unfavouriteContract(Integer requestid, Integer userid) {
		contractRepository.unfavouriteContract(requestid, userid);
	}
	
	@Override
	public Boolean checkFavourited(Integer requestid, Integer userid) {
		if (contractRepository.checkFavourited(requestid, userid) == 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public List<Contract> getAllExceptCurrent(Integer requestid) {
		return contractRepository.getAllExceptCurrent(requestid);
	}
	
	@Override
	public List<Contract> getRelatedContracts(Integer requestid) {
		return contractRepository.getRelatedContracts(requestid);
	}
	
	@Override
	public void unrelateContract(Integer requestid, Integer requestid2) {
		contractRepository.unrelateContract(requestid, requestid2);
	}
	
}
