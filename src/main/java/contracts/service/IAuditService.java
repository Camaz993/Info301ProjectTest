package contracts.service;

import java.util.List;

import contracts.domain.Audit;
import contracts.domain.Contract;

public interface IAuditService {

	public List<Audit> getAudit();

	public void addAudit(Audit newAudit);
	
	public void addAuditDetails(Contract contract);
	
	public void addAuditArchived(Contract contract);
	
	public List<Audit> getContractsByAuditsRequestID(Integer requestid);

}
