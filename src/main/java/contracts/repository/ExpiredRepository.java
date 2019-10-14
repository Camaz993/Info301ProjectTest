/** 
** The expired repoository class contains the JpaRepository set up
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.Expired;

public interface ExpiredRepository extends JpaRepository<Expired, Integer>{

}
