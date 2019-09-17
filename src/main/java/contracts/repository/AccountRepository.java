package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contracts.domain.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);

}
