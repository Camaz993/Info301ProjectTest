package contracts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import contracts.domain.Contract;
import contracts.domain.Current;
import contracts.domain.User;
import contracts.repository.CurrentRepository;

@Service
public class CurrentService implements ICurrentService {
	
	@Autowired
	CurrentRepository currentRepository;
	
	@Override
	public List<Current> getAllCurrent() {
		return currentRepository.getAllCurrent();
	}
	
	@Transactional
	@Override
	public void updateColours(Integer idcurrent_css, String background, String button, String links) {	
		currentRepository.updateColours(idcurrent_css, background, button, links);
	}

}
