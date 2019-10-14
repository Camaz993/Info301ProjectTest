/** 
 * The audit service class for accessing the audit table in the database
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.User;
import contracts.repository.AuditRepository;

@Service
public class AuditService implements IAuditService {

		
	@Autowired
	AuditRepository auditRepository;
	
	@Override
	public List<Audit> getAudit() {
		return auditRepository.findAll();
	}
	
	
	@Override
	public void addAudit(Audit newAudit) {
		
		try
		{
			auditRepository.save(newAudit);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	public void addAuditDetails(Contract contract, User user){
		Audit newAudit = new Audit();
		newAudit.setUserid(user);
		newAudit.setRequestedid(contract);
		newAudit.setDate(contract.getDate_updated());
		newAudit.setAdd_contract("Y");
		addAudit(newAudit);
	}
	
	public void addAuditArchived(Contract contract, User user){
		Audit newAudit = new Audit();
		newAudit.setUserid(user);
		newAudit.setRequestedid(contract);
		newAudit.setDate(contract.getDate_updated());
		newAudit.setArchived_contract("Y");
		addAudit(newAudit);
	}
	
	
	
	@SuppressWarnings("unused")
	public void updateAudit() {	
		Audit newAudit = new Audit();
	}
	
	public Audit auditObject(Contract contract) {
		Audit newAudit = new Audit();
		newAudit.setUserid(contract.getUser());
		newAudit.setRequestedid(contract);
		newAudit.setDate(contract.getDate_updated());
		return newAudit;
	}
	
	 public List<Audit> getContractsByAuditsRequestID(Integer requestid){
		return auditRepository.getContractsByAuditsRequestID(requestid);
	 }
	
}
