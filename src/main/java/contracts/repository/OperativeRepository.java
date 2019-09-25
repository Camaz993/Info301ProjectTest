package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.InNegotiation;

public interface OperativeRepository extends JpaRepository<InNegotiation, Integer>{

}
