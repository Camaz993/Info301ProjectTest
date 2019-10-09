package contracts.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contracts.domain.StatusLink;
import contracts.repository.StatusLinkRepository;


@Service
public class StatusLinkService implements IStatusLinkService{
	
	@Autowired
	StatusLinkRepository statuslinkRepository;
	
	public void setStatusLinkRepository(StatusLinkRepository statuslinkRepository) {
		this.statuslinkRepository = statuslinkRepository;
	}
	
	@Override
	public void addStatusLink(StatusLink statuslink) {
		
		try
		{
			statuslinkRepository.save(statuslink);
		} catch(javax.validation.ConstraintViolationException e)
		{ 
			throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
		}catch (Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
	
	@Override
	public StatusLink update(StatusLink status) {	
		return statuslinkRepository.save(status);
	}

	@Override
	public List<StatusLink> getAllStatus() {
		return statuslinkRepository.findAll();
	}
	
	
}
