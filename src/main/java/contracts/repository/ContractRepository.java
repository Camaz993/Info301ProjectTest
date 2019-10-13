/** 
** The contract repository class contains the JpaRepository set up and related queries
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
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

	//select the contracts that relate to the search
	@Query(value = "SELECT * FROM CONTRACT c WHERE c.Agreement_Title LIKE %:search%", nativeQuery = true)
    public List<Contract> searchContracts(@Param("search") String search);
  
	/*@Query(value = "SELECT * FROM CONTRACT c WHERE c.Agreement_Title LIKE :word or c.Description LIKE word")
	public List<Contract> searchContracts(String search);*/
    
	//select the contracts that match the searched location
	@Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Location) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchLocation(@Param("search") String search);
    
	//select the contract that match the searched contract type
    @Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Type) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
    public List<Contract> searchContractType(@Param("search") String search);
    
    //archive a contract
    @Query(value = "INSERT INTO ARCHIVED FROM CONTRACT WHERE ARCHIVED = 'true'", nativeQuery = true)
    public void archiveContract(Contract archivedContract);

    //get all contracts ordered by ascending id
    @Query(value = "SELECT * FROM CONTRACT WHERE LOWER(Agreement_Title) LIKE CONCAT(LOWER(:search), '%') OR RequestID LIKE CONCAT(:search, '%')", nativeQuery = true)
	public List<Contract> findAllByOrderByIdAsc();

    //get all archived contracts
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.archived = 'T'", 
    		  nativeQuery = true)
    public List<Contract> getArchivedContracts();
    
    //get all current contracts
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.archived = 'F' OR c.archived IS NULL", 
  		  nativeQuery = true)
    public List<Contract> getCurrentContracts();
    
    //get all contracts associated with the given user
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.userid = ?1 AND archived = 'F'", nativeQuery = true)
    public List<Contract> getContractsByUser(Integer userid);
    
    //get all contracts not assigned to a user
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.userid IS NULL", nativeQuery = true)
    public List<Contract> getNullUserContracts();
    
    //get all contracts that have been favourited by the current user
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.requestid IN (select requestid FROM favourited WHERE userid = ?1);", 
    		  nativeQuery = true)
    public List<Contract> getFavouritedContracts(Integer userid);
    
    //find the newest contract
    @Query(value = "SELECT MAX(requestid) FROM CONTRACT c", nativeQuery = true)
    public Integer findNewestContract();
    
    //unfavourite the given contract
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM FAVOURITED WHERE requestid = ?1 AND userid = ?2", nativeQuery = true)
    public void unfavouriteContract(Integer requestid, Integer userid);
    
    //get the favourited contracts
    @Query(value = "SELECT COUNT(*) FROM FAVOURITED WHERE requestid = ?1 AND userid = ?2 limit 1", nativeQuery = true)
    public Integer checkFavourited(Integer requestid, Integer userid);
    
    //get all contracts apart from the given
    @Query(value = "SELECT * FROM CONTRACT c WHERE c.requestid != ?1", nativeQuery = true)
    public List<Contract> getAllExceptCurrent(Integer requestid);
    
    //get all related contracts to the given contract
    @Query(value = "SELECT * FROM CONTRACT WHERE requestid IN (select requestid_relatedto FROM related_agreements WHERE requestid_related= ?1)", nativeQuery=true)
    public List<Contract> getRelatedContracts(Integer requestid); 
    
}

