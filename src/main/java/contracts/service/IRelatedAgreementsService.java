/** 
 * The relatedagreements interface for the service class
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.service;

import java.util.Optional;

import contracts.domain.RelatedAgreements;

public interface IRelatedAgreementsService {

	void addRelatedAgreements(RelatedAgreements newRelatedAgreement);
	
	public Integer findNewestRelated();
	
	public Optional<RelatedAgreements> findbyId(Integer id_relatedagreement);

}
