package restapp.equipment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipmentService {
	private EquipmentRepository equipmentRepository;
	
	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}
	
	List<EquipmentDto> findAllEquipments() {
		return equipmentRepository
				.findAll()
				.stream()
				.map(EquipmentMapper::equipmentToDto)
				.collect(Collectors.toList());
	}
	
	List<EquipmentDto> findByName(String name) {
		return equipmentRepository
				.findByName(name)
				.stream()
				.map(EquipmentMapper::equipmentToDto)
				.collect(Collectors.toList());
	}
	
	Optional<EquipmentDto> findBySerialNumber(String serialNumber) {
		return equipmentRepository
				.findBySerialNumber(serialNumber)
				.map(EquipmentMapper::equipmentToDto);
	}
	
	EquipmentDto saveEquipment(EquipmentDto equipmentDto) {
		Optional<Equipment> checkIsExist = equipmentRepository.findBySerialNumber(equipmentDto.getSerialNumber());
		checkIsExist.ifPresent(e -> {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "An equipment with this SERIAL NUMBER already exists");
		});
		Equipment equipment = EquipmentMapper.dtoToEquipment(equipmentDto);
		Equipment equipmentToSave = equipmentRepository.save(equipment);
		return EquipmentMapper.equipmentToDto(equipmentToSave);
	}
	
	EquipmentDto update(EquipmentDto equipmentDto) {
		Optional<Equipment> checkIfExist = equipmentRepository.findBySerialNumber(equipmentDto.getSerialNumber());
		checkIfExist.ifPresent(e -> {
			if (!e.getId().equals(equipmentDto.getId()))
				throw new ResponseStatusException(HttpStatus.CONFLICT, "An equipment with this SERIAL NUMBER already exists");
		});
		Equipment equipment = EquipmentMapper.dtoToEquipment(equipmentDto);
		Equipment equipmentToSave = equipmentRepository.save(equipment);
		return EquipmentMapper.equipmentToDto(equipmentToSave);
	}
	
//	//TODO
//	EquipmentDto returnEquipment(EquipmentDto equipmentDto) {
//		Optional<Equipment> checkIsExist = equipmentRepository.findBySerialNumber(equipmentDto.getSerialNumber());
//		Equipment equipment = checkIsExist.orElseThrow(() -> new RuntimeException("No found equipment with this SERIAL NUMBER"));
//		
//		return EquipmentMapper.equipmentToDto(equipment);
//	}
}
