package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.Operative;

public interface OperativeRepository extends JpaRepository<Operative, Integer>{

}
