package restapp.rentcard;

public class EmployeeRentCardMapper {

	public static EmployeeRentCardDto rentCardToDto(RentCard card) {
		EmployeeRentCardDto employeeRentCardDto = new EmployeeRentCardDto();
		employeeRentCardDto.setId(card.getId());
		employeeRentCardDto.setRentalTime(card.getRentalTime());
		employeeRentCardDto.setActive(card.isActive());
		employeeRentCardDto.setEquipmentId(card.getEquipment().getId());
		employeeRentCardDto.setEquipmentName(card.getEquipment().getName());
		employeeRentCardDto.setEquipmentSerialNumber(card.getEquipment().getSerialNumber());
		employeeRentCardDto.setEquipmentQuantity(card.getQuantity());
		return employeeRentCardDto;
	}
}
