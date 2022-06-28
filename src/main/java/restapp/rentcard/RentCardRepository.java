package restapp.rentcard;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentCardRepository extends JpaRepository<RentCard, Long>{

	Optional<RentCard> findById(Long id);
	
	@Query("SELECT c FROM RentCard c WHERE c.active = false ORDER BY c.rentalTime DESC")
	List<RentCard> findAllInactiveOrderByDate();
	
	@Query("SELECT c FROM RentCard c WHERE c.active = true ORDER BY c.rentalTime DESC")
	List<RentCard> findAllActiveOrderByDate();
}
