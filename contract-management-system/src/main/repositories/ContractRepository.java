package src.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import src.main.domain.contract;

public interface ContractRepository extends JpaRepository<contract, Long> {


}
