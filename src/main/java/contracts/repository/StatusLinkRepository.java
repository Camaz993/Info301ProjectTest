package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.StatusLink;

public interface StatusLinkRepository extends JpaRepository<StatusLink, Integer>{

}
