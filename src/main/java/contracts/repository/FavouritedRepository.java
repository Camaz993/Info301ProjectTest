/** 
** The favourited repository class contains the JpaRepository set up
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contracts.domain.Favourited;

@Repository
public interface FavouritedRepository extends JpaRepository<Favourited, Integer>{

}
