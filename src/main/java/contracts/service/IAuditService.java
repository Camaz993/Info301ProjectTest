package contracts.service;

import java.util.List;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.User;

public interface IAuditService {

	public List<Audit> getAudit();

	public void addAudit(Audit newAudit);
	
	public void addAuditDetails(Contract contract, User user);
	
	public void addAuditArchived(Contract contract, User user);
	
	public List<Audit> getContractsByAuditsRequestID(Integer requestid);

}
