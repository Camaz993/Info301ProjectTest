package contracts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.Favourited;
import contracts.repository.FavouritedRepository;

@Service
public class FavouritedService implements IFavouritedService {
	
	@Autowired
	FavouritedRepository favouritedRepository;
	
	@Override
	public void addFavourite(Favourited newFavourite) {
		
		try
		{
			favouritedRepository.save(newFavourite);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}


}
