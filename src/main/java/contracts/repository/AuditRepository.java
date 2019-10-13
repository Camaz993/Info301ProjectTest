/** 
** The audit repository class contains the JpaRepository set up and related query
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import contracts.domain.Audit;
import contracts.domain.Contract;
import contracts.domain.User;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	//get all of the audits associated with the given requestid
	  @Query(value = "SELECT * FROM AUDIT WHERE fk_requestid_audit = ?1", nativeQuery = true)
	    public List<Audit> getContractsByAuditsRequestID(Integer requestid);
    
}

