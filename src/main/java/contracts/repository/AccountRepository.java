package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import contracts.domain.User;

public interface AccountRepository extends JpaRepository<User, Integer> {

}
