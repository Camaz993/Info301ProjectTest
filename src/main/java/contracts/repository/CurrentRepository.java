package contracts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import contracts.domain.Current;

public interface CurrentRepository extends JpaRepository<Current, Integer> {
	
	@Query(value = "SELECT * FROM current_css", 
	   		  nativeQuery = true)
	public List<Current> getAllCurrent();
	
	@Modifying
	 @Query(value = "UPDATE current_css c SET c.background = ?2, c.button = ?3, c.links = ?4 WHERE idcurrent_css = ?1", 
   		  nativeQuery = true)
	public void updateColours(Integer idcurrent_css, String background, String button, String links);

}
