package contracts.service;

import java.util.List;

import contracts.domain.contract;

public interface IContractService {
	
	public void addContract(contract newContract);

	public List<contract> getAllContracts();
	
	public List<contract> searchContracts(String search);
	
	public List<contract> searchLocation(String search);
}
