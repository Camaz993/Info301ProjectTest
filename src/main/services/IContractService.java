package Contract.Management.System.Gradle;

import main.domain.contract;
import java.util.List;

public interface IContractService {
	
	public void addContract(contract newContract);

	public List<contract> getAllContracts();
}
