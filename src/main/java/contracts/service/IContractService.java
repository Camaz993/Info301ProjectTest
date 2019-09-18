package contracts.service;

import java.util.List;
import java.util.Optional;

import contracts.domain.Contract;
import contracts.domain.Status;
import contracts.domain.User;

public interface IContractService {
	
	public void addContract(Contract newContract);
	
	public Optional<Contract> findContract(Integer id);

	public List<Contract> getAllContracts();
	
	public Optional<User> findById(Integer id);
	
	public List<Contract> searchContracts(String search);
	
	public List<Contract> searchLocation(String search);
	
	public List<Contract> searchContractType(String search);
	
	public Contract update(Contract contract);

	public List<User> getAllUsers();
	
	public void archiveContract(Contract archivedContract);
	
	public List<Contract> getArchivedContracts();

	public List<Contract> findAllByOrderByIdAsc();
	
	public void unarchiveContract(Contract unarchiveContract);
	
	public List<Contract> getUnarchivedContracts();
	
	public List<Contract> getContractsByUser(Integer userid);
}
