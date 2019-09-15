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
	
	public void updateDetails(Integer requestid, User user, List<Status> statusList, String agreement_title, 
			String agreement_type, String description, String agreement_location, String language, 
			String region, String related_agreements);

	public List<User> getAllUsers();

}
