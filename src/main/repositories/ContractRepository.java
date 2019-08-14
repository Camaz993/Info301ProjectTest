package main.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import main.domain.contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {


}
