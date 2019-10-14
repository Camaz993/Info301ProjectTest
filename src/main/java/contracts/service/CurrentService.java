/** 
 * The current service class for accessing the current table in the database
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import contracts.domain.Current;
import contracts.repository.CurrentRepository;

@Service
public class CurrentService implements ICurrentService {
	
	@Autowired
	CurrentRepository currentRepository;
	
	@Override
	public List<Current> getAllCurrent() {
		return currentRepository.getAllCurrent();
	}
	
	@Override
	public void addCurrent(Current newCurrent) {
		
		try
		{
			currentRepository.save(newCurrent);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	@Transactional
	@Override
	public void updateColours(Integer idcurrent_css, String colour) {	
		currentRepository.updateColours(idcurrent_css, colour);
	}
	
	@Override
	public Integer getCurrent(){
		return currentRepository.getCurrent();
	}

}
