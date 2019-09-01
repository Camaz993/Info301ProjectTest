package contracts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import contracts.domain.contract;

public interface ContractRepository extends JpaRepository<contract, Long> {

	@Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Title) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<contract> searchContracts(@Param("search") String search);
}
