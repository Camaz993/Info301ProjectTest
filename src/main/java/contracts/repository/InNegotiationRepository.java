package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import contracts.domain.Contract;
import contracts.domain.InNegotiation;

public interface InNegotiationRepository extends JpaRepository<InNegotiation, Integer>{

}
