/** 
** The innegotiation repository class contains the JpaRepository set up
 * @author Alice, Caleb, Laurie, Natalie, Poppy
 */
package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.InNegotiation;

public interface InNegotiationRepository extends JpaRepository<InNegotiation, Integer>{

}
