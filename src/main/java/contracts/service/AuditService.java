package contracts.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.User;
import contracts.repository.AccountRepository;
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
	
	public void addAuditDetails(Contract contract){
		Audit newAudit = new Audit();
		newAudit.setUserid(contract.getUser());
		newAudit.setRequestedid(contract);
		newAudit.setDate(contract.getDate_updated());
		newAudit.setAdd_contract("Y");
		addAudit(newAudit);
	}
	
	
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
