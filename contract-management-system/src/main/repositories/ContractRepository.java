package src.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.domain.contract;

public interface ContractRepository extends JpaRepository<contract, Long> {


}
