package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import contracts.domain.user;

public interface AccountRepository extends JpaRepository<user, Integer> {

}
