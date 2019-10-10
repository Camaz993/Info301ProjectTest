package contracts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import contracts.domain.Contract;
import contracts.domain.Favourited;
import contracts.domain.StatusLink;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

	@Query(value = "SELECT * FROM CONTRACT c WHERE c.Agreement_Title = ?1", nativeQuery = true)
    public List<Contract> searchContracts(String search);
  
	/*@Query(value = "SELECT * FROM CONTRACT c WHERE c.Agreement_Title LIKE :word or c.Description LIKE word")
	public List<Contract> searchContracts(String search);*/
    
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
    
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.userid = ?1 AND archived = 'F'", nativeQuery = true)
    public List<Contract> getContractsByUser(Integer userid);
    
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.userid IS NULL", nativeQuery = true)
    public List<Contract> getNullUserContracts();
    
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.requestid IN (select requestid FROM favourited WHERE userid = ?1);", 
    		  nativeQuery = true)
    public List<Contract> getFavouritedContracts(Integer userid);
    
    @Query(value = "SELECT MAX(requestid) FROM CONTRACT c", nativeQuery = true)
    public Integer findNewestContract();
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM FAVOURITED WHERE requestid = ?1 AND userid = ?2", nativeQuery = true)
    public void unfavouriteContract(Integer requestid, Integer userid);
    
    @Query(value = "SELECT COUNT(*) FROM FAVOURITED WHERE requestid = ?1 AND userid = ?2 limit 1", nativeQuery = true)
    public Integer checkFavourited(Integer requestid, Integer userid);
}

