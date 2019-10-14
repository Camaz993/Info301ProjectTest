/** 
 * The currentcss interface for the service class
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;

import java.util.Optional;

import contracts.domain.CurrentCss;

public interface ICurrentCssService {

	public void addCurrentCss(CurrentCss currentCss);

	public CurrentCss update(CurrentCss currentCss);

	public Integer getCurrentCss();

	public Optional<CurrentCss> findCss(Integer id);
}
