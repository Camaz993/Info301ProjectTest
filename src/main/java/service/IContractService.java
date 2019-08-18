package service;

import java.util.List;

import domain.contract;

public interface IContractService {
	
	public void addContract(contract newContract);

	public List<contract> getAllContracts();
}
