package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import contracts.domain.RelatedAgreements;

@Repository
public interface RelatedAgreementsRepository extends JpaRepository<RelatedAgreements, Integer> {

}
