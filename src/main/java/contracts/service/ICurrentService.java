package contracts.service;

import java.util.List;
import java.util.Optional;

import contracts.domain.Contract;
import contracts.domain.Current;

public interface ICurrentService {
	
	public List<Current> getAllCurrent();
	
	public void updateBackground(Integer currentid_css, String background);

}
