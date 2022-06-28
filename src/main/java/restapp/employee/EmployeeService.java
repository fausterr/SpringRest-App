package restapp.employee;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import restapp.rentcard.EmployeeRentCardDto;
import restapp.rentcard.EmployeeRentCardMapper;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	List<EmployeeDto> findAll() {
		return employeeRepository
				.findAll()
				.stream()
				.map(EmployeeMapper::employeeToDto)
				.collect(Collectors.toList());
	}
	
	List<EmployeeDto> findAllByLastName(String lastName) {
		return employeeRepository
				.findAllByLastNameContainingIgnoreCase(lastName)
				.stream()
				.map(EmployeeMapper::employeeToDto)
				.collect(Collectors.toList());
	}
	
	Optional<EmployeeDto> findByEmployeeId(String employeeId) {
		return employeeRepository
				.findByEmployeeID(employeeId)
				.map(EmployeeMapper::employeeToDto);
	}

	EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Optional<Employee> checkIsExist = employeeRepository.findByEmployeeID(employeeDto.getEmployeeID());
		checkIsExist.ifPresent(e -> {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "An employee with this ID already exists");
		});
		Employee employee = EmployeeMapper.dtoToEmployee(employeeDto);
		Employee employeeToSave = employeeRepository.save(employee);
		return EmployeeMapper.employeeToDto(employeeToSave);
	}
	
	EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		Optional<Employee> checkIsExist = employeeRepository.findByEmployeeID(employeeDto.getEmployeeID());
		checkIsExist.ifPresent(e -> {
			if (!e.getId().equals(employeeDto.getId()))
				throw new ResponseStatusException(HttpStatus.CONFLICT, "An employee with this ID already exists");
		});
		Employee employee = EmployeeMapper.dtoToEmployee(employeeDto);
		Employee employeeToSave = employeeRepository.save(employee);
		return EmployeeMapper.employeeToDto(employeeToSave);
	}
	
	List<EmployeeRentCardDto> getAllUserCards(String employeeId) {
		return employeeRepository
				.findByEmployeeID(employeeId)			
				.map(Employee::getRentCards)
				.orElseThrow() //tutaj wrzucic do srodka jakis wyjatek
				.stream()
				.map(EmployeeRentCardMapper::rentCardToDto)
				.collect(Collectors.toList());
	}
	
}
