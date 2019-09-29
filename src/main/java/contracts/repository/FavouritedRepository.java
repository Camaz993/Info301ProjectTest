package contracts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contracts.domain.Contract;
import contracts.domain.Favourited;

@Repository
public interface FavouritedRepository extends JpaRepository<Favourited, Integer>{

}
