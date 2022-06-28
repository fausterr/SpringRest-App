package restapp.rentcard;

import java.time.LocalDateTime;

public class RentCardDto {

	private Long id;
	private LocalDateTime rentalTime;
	private boolean active;
	private String employeeId;
	private String serialNumber;
	private int quantity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getRentalTime() {
		return rentalTime;
	}
	public void setRentalTime(LocalDateTime rentalTime) {
		this.rentalTime = rentalTime;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
