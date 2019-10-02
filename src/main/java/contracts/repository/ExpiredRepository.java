package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.Expired;

public interface ExpiredRepository extends JpaRepository<Expired, Integer>{

}
