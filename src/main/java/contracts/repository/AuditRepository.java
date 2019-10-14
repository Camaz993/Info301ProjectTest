/** 
** The audit repository class contains the JpaRepository set up and related query
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import contracts.domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	//get all of the audits associated with the given requestid
	  @Query(value = "SELECT * FROM AUDIT WHERE fk_requestid_audit = ?1", nativeQuery = true)
	    public List<Audit> getContractsByAuditsRequestID(Integer requestid);
    
}

