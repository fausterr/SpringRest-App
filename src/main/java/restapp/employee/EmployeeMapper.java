package restapp.employee;

public class EmployeeMapper {

	static EmployeeDto employeeToDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setEmployeeID(employee.getEmployeeID());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setDepartment(employee.getDepartment());
		return employeeDto;
	}
	
	static Employee dtoToEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setEmployeeID(employeeDto.getEmployeeID());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setDepartment(employeeDto.getDepartment());
		return employee;
	}
}
