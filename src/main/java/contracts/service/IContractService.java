package contracts.service;

import java.util.List;
import java.util.Optional;

import contracts.domain.Contract;
import contracts.domain.Status;
import contracts.domain.User;

public interface IContractService {
	
	public void addContract(Contract newContract);

	public List<Contract> getAllContracts();
	
	public Optional<User> findById(Integer id);
	
	public List<Contract> searchContracts(String search);
	
	public List<Contract> searchLocation(String search);
	
	public List<Contract> searchContractType(String search);
	
	public void update(Integer requestid);

	public List<User> getAllUsers();

}
