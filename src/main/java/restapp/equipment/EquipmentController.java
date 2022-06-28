package restapp.equipment;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/restapp/equipment")
public class EquipmentController {
	
	private EquipmentService equipmentService;
	
	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
	
	@GetMapping("")
	public List<EquipmentDto> findAllOrByName(@RequestParam(required = false) String name) {
		if (name != null)
			return equipmentService.findByName(name);
		else
			return equipmentService.findAllEquipments();
	}
	
	@GetMapping("/{number}")
	public ResponseEntity<EquipmentDto> findBySerialNumber(@PathVariable String number) {
		return equipmentService
				.findBySerialNumber(number)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());		
	}
	
	@PostMapping("")
	public ResponseEntity<EquipmentDto> save(@RequestBody EquipmentDto equipmentDto) {
		if (equipmentDto.getId() != null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id of saving object must be null");
		EquipmentDto equipmentToSave = equipmentService.saveEquipment(equipmentDto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/{number}")
				.buildAndExpand(equipmentToSave.getSerialNumber())
				.toUri();
		return ResponseEntity.created(location).body(equipmentToSave);
	}

	@PutMapping("/{number}")
	public ResponseEntity<EquipmentDto> update(@PathVariable String number, 
			@RequestBody EquipmentDto equipmentDto) {
		if (!number.equals(equipmentDto.getSerialNumber()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no equipment with this serial number");	
		EquipmentDto equipmentToUpdate = equipmentService.update(equipmentDto);
		return ResponseEntity.ok(equipmentToUpdate);
	}
}
