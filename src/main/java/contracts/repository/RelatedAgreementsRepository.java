/** 
** The relatedagreements repository class contains the JpaRepository set up
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import contracts.domain.RelatedAgreements;

@Repository
public interface RelatedAgreementsRepository extends JpaRepository<RelatedAgreements, Integer> {
	
	//finds the newest from relatedagreements
	@Query(value = "SELECT MAX(idrelated_agreements) FROM related_agreements ", nativeQuery = true)
    public Integer findNewestRelated();

}
