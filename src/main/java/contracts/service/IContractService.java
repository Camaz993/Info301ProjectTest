package contracts.service;

import java.util.List;
import java.util.Optional;

import contracts.domain.contract;
import contracts.domain.user;

public interface IContractService {
	
	public void addContract(contract newContract);

	public List<contract> getAllContracts();
	
	public Optional<user> findById(Integer id);
	
	public List<contract> searchContracts(String search);
	
	public List<contract> searchLocation(String search);
	
	public List<contract> searchContractType(String search);
}
