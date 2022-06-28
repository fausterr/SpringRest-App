package restapp.employee;

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

import restapp.rentcard.EmployeeRentCardDto;

@RestController
@RequestMapping("/restapp/employee")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("")
	public List<EmployeeDto> findAllOrByLastName(@RequestParam(required = false) String lastName) {
		if (lastName != null)
			return employeeService.findAllByLastName(lastName);
		else
			return employeeService.findAll();
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> findByEmployeeId(@PathVariable String employeeId) {
		return employeeService
				.findByEmployeeId(employeeId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("")
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		if (employeeDto.getId() != null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id of saving object must be null");
		EmployeeDto employeeToSave = employeeService.saveEmployee(employeeDto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(employeeToSave.getId())
				.toUri();
		return ResponseEntity.created(location).body(employeeToSave);		
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String employeeId, @RequestBody EmployeeDto employeeDto) {
		if (!employeeId.equals(employeeDto.getEmployeeID()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no person with this id");
		EmployeeDto employeeToUpdate = employeeService.updateEmployee(employeeDto);
		return ResponseEntity.ok(employeeToUpdate);
	}	
	
	@GetMapping("{employeeId}/rentCards")
	public List<EmployeeRentCardDto> getAllUserRentCards(@PathVariable String employeeId) {
		return employeeService.getAllUserCards(employeeId);
	}
	
	/* test metods */

//	@GetMapping("/test")
//	public List<EmployeeRentCardDto> test() {
//		return employeeService.getAllUserCards("12345");
//	}

}
