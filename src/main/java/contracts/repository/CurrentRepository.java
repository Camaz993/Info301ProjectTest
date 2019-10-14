/** 
** The current repository class contains the JpaRepository set up and related queries
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import contracts.domain.Current;

public interface CurrentRepository extends JpaRepository<Current, Integer> {
	
	//get all of the objects in the current_css table
	@Query(value = "SELECT * FROM current_css", 
	   		  nativeQuery = true)
	public List<Current> getAllCurrent();
	
	//update the selected current
	@Modifying
	 @Query(value = "UPDATE current_css c SET c.colour = ?2 WHERE idcurrent_css = ?1", 
   		  nativeQuery = true)
	public void updateColours(Integer idcurrent_css, String colour);
	
	//select the newest object from current_css
    @Query(value = "SELECT MAX(idcurrent_css) FROM current_css c", nativeQuery = true)
    public Integer getCurrent();

}
