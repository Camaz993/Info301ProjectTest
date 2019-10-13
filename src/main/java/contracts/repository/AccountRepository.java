/** 
** The account repository class contains the JpaRepository set up and related query
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import contracts.domain.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);
	
	//get all of the user roles
	@Query(value = "SELECT role FROM USER", nativeQuery = true)
    public List<String> getUserRoles();

}
