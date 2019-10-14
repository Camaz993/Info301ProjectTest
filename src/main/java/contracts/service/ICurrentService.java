/** 
 * The current interface for the service class
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;

import java.util.List;
import java.util.Optional;

import contracts.domain.Contract;
import contracts.domain.Current;

public interface ICurrentService {
	
	public List<Current> getAllCurrent();
	
	public void updateColours(Integer currentid_css, String colour);

	public Integer getCurrent();

	public void addCurrent(Current newCurrent);

}
