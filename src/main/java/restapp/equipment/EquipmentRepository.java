package restapp.equipment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
	//znajdz wszystkie dostepne
	Optional<Equipment> findByName(String name);
	Optional<Equipment> findBySerialNumber(String serialNumber);
	
	@Modifying
	@Query("UPDATE Equipment e SET e.quantity = e.quantity + :quantity WHERE e.id = :id")
	void setByQuantity(@Param(value = "quantity") int quantity, @Param(value = "id") long id);
	
//	@Modifying
//	@Query("UPDATE Equipment e SET e.quantity = 888 WHERE e.id = :id")
//	void setByQuantity(@Param(value = "id") long id);
}
