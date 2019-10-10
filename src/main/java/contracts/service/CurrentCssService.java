package contracts.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.CurrentCss;
import contracts.repository.CurrentCssRepository;


@Service
public class CurrentCssService implements ICurrentCssService{
	
	@Autowired
	CurrentCssRepository currentCssRepository;
	
	public void setCurrentCssRepository(CurrentCssRepository currentCssRepository) {
		this.currentCssRepository = currentCssRepository;
	}
	
	@Override
	public void addCurrentCss(CurrentCss currentCss) {
		
		try
		{
			currentCssRepository.save(currentCss);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	@Override
	public CurrentCss update(CurrentCss currentCss) {	
		return currentCssRepository.save(currentCss);
	}
	
	@Override
    public Optional<CurrentCss> findCss(Integer id) {
        return currentCssRepository.findById(id);
    }
	
	@Override
	public Integer getCurrentCss() {
		return currentCssRepository.findNewestCss();
	}
	
}
