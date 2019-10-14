/** 
** The currentcss repository class contains the JpaRepository set up and related queries
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import contracts.domain.CurrentCss;

public interface CurrentCssRepository extends JpaRepository<CurrentCss, Integer>{
	
	//select the newest from current_css
    @Query(value = "SELECT MAX(idcurrent_css) FROM CURRENT_CSS c", nativeQuery = true)
    public Integer findNewestCss();

}
