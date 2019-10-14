/** 
 * The innegotiation service class for accessing the innegotiation table in the database
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.InNegotiation;
import contracts.repository.AccountRepository;
import contracts.repository.InNegotiationRepository;


@Service
public class InNegotiationService implements IInNegotiationService{
	
	@Autowired
	InNegotiationRepository inNegotiationRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public void setInNegotiationRepository(InNegotiationRepository inNegotiationRepository) {
		this.inNegotiationRepository = inNegotiationRepository;
	}
	
	@Override
	public void addInNegotiation(InNegotiation newInNegotiation) {
		
		try
		{
			inNegotiationRepository.save(newInNegotiation);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	@Override
	public InNegotiation update(InNegotiation in_negotiation) {	
		return inNegotiationRepository.save(in_negotiation);
	}
	
	
}
