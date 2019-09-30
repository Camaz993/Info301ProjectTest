package contracts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import contracts.domain.Contract;
import contracts.domain.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);
	
	@Query(value = "SELECT role FROM USER", nativeQuery = true)
    public List<String> getUserRoles();

}
