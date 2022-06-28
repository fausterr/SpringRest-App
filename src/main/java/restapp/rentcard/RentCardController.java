package restapp.rentcard;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import restapp.equipment.Equipment;
import restapp.equipment.EquipmentDto;

@RestController
@RequestMapping("/restapp/rentCard")
public class RentCardController {

	private RentCardService rentCardService;
	
	RentCardController(RentCardService rentCardService) {
		this.rentCardService = rentCardService;
	}
	
	@PostMapping("")
	public ResponseEntity<RentCardDto> saveRentCard(@RequestBody RentCardDto rentCardDto) {
		RentCardDto cardToSave = rentCardService.createRentCard(rentCardDto);
		try {
			cardToSave = rentCardService.createRentCard(rentCardDto);
		} catch (RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}	
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cardToSave.getId())
				.toUri();
		return ResponseEntity.created(location).body(cardToSave);
	}
	
	//DODAC METODE DO NIE AKTYWNYCH +
	//DODAC METODY DO USUWANIA USEROW ITD
	//DODAC LOGIKE PRZYWRACANIA PRZEDMIOTOW
	
	@GetMapping("")
	public List<RentCardDto> findAllActive() {  //TU ZMIENIC I DAC TYLKO AKTYWNE
		return rentCardService.findAllActiveCards();
	}
	
	@GetMapping("/inactive")
	public List<RentCardDto> findAllInactive() {  
		return rentCardService.findAllInactiveCards();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RentCardDto> findById(@PathVariable Long id) {
		return rentCardService
				.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{id}/return")
	public ResponseEntity<RentCardDto> returnEquipment(@PathVariable Long id) {
//		Optional<RentCardDto> findCard = rentCardService.findById(id);
//		if (!id.equals(rentCardDto.getId()))
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no rentcard with this serial number");
	
		RentCardDto cardToReturn = rentCardService
				.returnEquipment(rentCardService.findById(id).orElseThrow());		
		return ResponseEntity.ok(cardToReturn);	
	}
}
