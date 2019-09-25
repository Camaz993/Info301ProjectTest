package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.InNegotiation;

public interface InNegotiationRepository extends JpaRepository<InNegotiation, Integer>{

}
