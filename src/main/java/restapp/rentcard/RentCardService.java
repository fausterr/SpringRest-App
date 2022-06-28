package restapp.rentcard;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import restapp.employee.Employee;
import restapp.employee.EmployeeRepository;
import restapp.equipment.Equipment;
import restapp.equipment.EquipmentRepository;

@Service
public class RentCardService {

	private RentCardRepository rentCardRepository;
	private EmployeeRepository employeeRepository;
	private EquipmentRepository equipmentRepository;

	public RentCardService(RentCardRepository rentCardRepository,
			EmployeeRepository employeeRepository,
			EquipmentRepository equipmentRepository) {
		this.rentCardRepository = rentCardRepository;
		this.equipmentRepository = equipmentRepository;
		this.employeeRepository = employeeRepository;
	}
	
	RentCardDto createRentCard(RentCardDto cardDto)   {		
		Optional<Employee> employee = employeeRepository.findByEmployeeID(cardDto.getEmployeeId());
		Optional<Equipment> equipment = equipmentRepository.findBySerialNumber(cardDto.getSerialNumber());
		RentCard cardToSave = new RentCard();
		cardToSave.setEmployee(employee.orElseThrow(() -> new RuntimeException("There is no employee with this id: " + cardDto.getEmployeeId()))); 
		cardToSave.setEquipment(equipment.orElseThrow(() -> new RuntimeException("There is no equipment with this id: " + cardDto.getSerialNumber())));
		cardToSave.setRentalTime(LocalDateTime.now());
		cardToSave.setActive(true);
		cardToSave.setQuantity(cardDto.getQuantity());	
		equipmentRepository.setByQuantity(cardDto.getQuantity()*(-1), cardToSave.getEquipment().getId());	
		return RentCardMapper.rentCardToDto(rentCardRepository.save(cardToSave));
	}
	
	List<RentCardDto> findAllActiveCards() {
		return rentCardRepository
				.findAllActiveOrderByDate()
				.stream()
				.map(RentCardMapper::rentCardToDto)
				.collect(Collectors.toList());
	}
	
	List<RentCardDto> findAllInactiveCards() {
		return rentCardRepository
				.findAllInactiveOrderByDate()
				.stream()
				.map(RentCardMapper::rentCardToDto)
				.collect(Collectors.toList());
	}
	
	Optional<RentCardDto> findById(Long id) {
		return rentCardRepository
				.findById(id)				
				.map(RentCardMapper::rentCardToDto);
	}
	
	RentCardDto returnEquipment(RentCardDto rentCardDto) {		
		RentCard rentCard = RentCardMapper.dtoToRentCard(rentCardDto);		
		rentCard.setEmployee(employeeRepository
				.findByEmployeeID(rentCardDto.getEmployeeId())
				.orElseThrow(IllegalArgumentException::new));
		rentCard.setEquipment(equipmentRepository
				.findBySerialNumber(rentCardDto.getSerialNumber())
				.orElseThrow(IllegalArgumentException::new));
		rentCard.setActive(false);	
		equipmentRepository.setByQuantity(rentCardDto.getQuantity(), rentCard.getEquipment().getId());		
		RentCard rentCardToSave = rentCardRepository.save(rentCard);
		return RentCardMapper.rentCardToDto(rentCardToSave);
	}
	
	
}
