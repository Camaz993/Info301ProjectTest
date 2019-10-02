package contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.RelatedAgreements;
import contracts.repository.RelatedAgreementsRepository;

@Service
public class RelatedAgreementsService implements IRelatedAgreementsService {
	
	@Autowired
	RelatedAgreementsRepository relatedAgreementsRepository;
	
	@Override
	public void addRelatedAgreements(RelatedAgreements newRelatedAgreement) {
		
		try
		{
			relatedAgreementsRepository.save(newRelatedAgreement);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}

}
