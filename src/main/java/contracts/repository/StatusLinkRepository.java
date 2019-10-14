/** 
** The statuslink repository class contains the JpaRepository set up
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.StatusLink;

public interface StatusLinkRepository extends JpaRepository<StatusLink, Integer>{

}
