/** 
** The relatedagreements repository class contains the JpaRepository set up
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import contracts.domain.RelatedAgreements;

@Repository
public interface RelatedAgreementsRepository extends JpaRepository<RelatedAgreements, Integer> {
	
	//finds the newest from relatedagreements
	@Query(value = "SELECT MAX(idrelated_agreements) FROM related_agreements ", nativeQuery = true)
    public Integer findNewestRelated();

	@Transactional
    @Modifying
    @Query(value = "DELETE FROM RELATED_AGREEMENTS WHERE requestid_related = requestid_relatedto", nativeQuery=true)
	public void unrelateContract(RelatedAgreements relatedAgreement); 

}
