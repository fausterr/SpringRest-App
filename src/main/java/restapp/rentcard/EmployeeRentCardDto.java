package restapp.rentcard;

import java.time.LocalDateTime;

public class EmployeeRentCardDto {

	private Long id;
    private LocalDateTime rentalTime;
    private boolean active;
    private Long equipmentId;
    private String equipmentName;
    private String equipmentSerialNumber;
    private int equipmentQuantity;
    
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
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentSerialNumber() {
		return equipmentSerialNumber;
	}
	public void setEquipmentSerialNumber(String equipmentSerialNumber) {
		this.equipmentSerialNumber = equipmentSerialNumber;
	}
	public int getEquipmentQuantity() {
		return equipmentQuantity;
	}
	public void setEquipmentQuantity(int equipmentQuantity) {
		this.equipmentQuantity = equipmentQuantity;
	}
    
    
}
