package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import contracts.domain.CurrentCss;

public interface CurrentCssRepository extends JpaRepository<CurrentCss, Integer>{
	
    @Query(value = "SELECT MAX(idcurrent_css) FROM CURRENT_CSS c", nativeQuery = true)
    public Integer findNewestCss();

}
