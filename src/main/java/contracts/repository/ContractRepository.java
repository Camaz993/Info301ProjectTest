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
    
    @Modifying
	@Query(value = "UPDATE CONTRACT SET userid = :user, Agreement_Title = :agreement_title, Agreement_Type = :agreement_type, Description = :description, Agreement_Location = :agreement_location, Language = :language, Region = :region, Related_Agreements = :related_agreements", nativeQuery = true)
	public void updateDetails(@Param("user") User user, @Param("agreement_title") String agreement_title,
			@Param("agreement_type") String agreement_type, @Param("description") String description, @Param("agreement_location") String agreement_location, 
			@Param("language") String language, @Param("region") String region, @Param("related_agreements") String related_agreements);
    
    @Query(value = "INSERT INTO ARCHIVED FROM CONTRACT WHERE ARCHIVED = 'true'", nativeQuery = true)
    public void archiveContract(Contract archivedContract);
}
