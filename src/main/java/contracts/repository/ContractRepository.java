package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contracts.domain.contract;

public interface ContractRepository extends JpaRepository<contract, Long> {


}
