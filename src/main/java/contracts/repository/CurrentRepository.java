package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import contracts.domain.Contract;
import contracts.domain.Current;

public interface CurrentRepository extends JpaRepository<Current, Integer> {
	
	@Modifying
	 @Query(value = "UPDATE current_css c SET c.background = ?2 WHERE idcurrent_css = ?1", 
   		  nativeQuery = true)
	public void updateBackground(Integer idcurrent_css, String background);

}
