package contracts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import contracts.domain.Contract;
import contracts.domain.User;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

	@Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Title) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchContracts(@Param("search") String search);
    
	@Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Location) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchLocation(@Param("search") String search);
    
    @Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Type) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchContractType(@Param("search") String search);
    
    @Query(value = "INSERT INTO ARCHIVED FROM CONTRACT WHERE ARCHIVED = 'true'", nativeQuery = true)
    public void archiveContract(Contract archivedContract);

    @Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Title) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
	public List<Contract> findAllByOrderByIdAsc();

    @Query(value = "SELECT * FROM CONTRACT c WHERE c.archived = 'T'", 
    		  nativeQuery = true)
    public List<Contract> getArchivedContracts();
    
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.archived = 'F' OR c.archived IS NULL", 
  		  nativeQuery = true)
    public List<Contract> getCurrentContracts();
    
    @Query(value = "INSERT INTO ARCHIVED FROM CONTRACT WHERE ARCHIVED = 'false'", nativeQuery = true)
    public void unarchiveContract(Contract unarchivedContract);
    
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.userid = ?1 AND archived = 'F'", nativeQuery = true)
    public List<Contract> getContractsByUser(Integer userid);
    
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.userid IS NULL ORDER BY c.date_updated LIMIT 10", nativeQuery = true)
    public List<Contract> getNullUserContracts();
    
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.requestid IN (select requestid FROM favourited WHERE userid = ?1);", 
    		  nativeQuery = true)
    public List<Contract> getFavouritedContracts(Integer userid);
}

