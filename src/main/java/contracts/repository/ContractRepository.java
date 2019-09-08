package contracts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import contracts.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

	@Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Title) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchContracts(@Param("search") String search);
    
	@Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Location) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchLocation(@Param("search") String search);
    
    @Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Type) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchContractType(@Param("search") String search);

	public Optional<Contract> findById(Integer requestid);
}
