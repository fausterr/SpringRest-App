package restapp.rentcard;

import restapp.equipment.EquipmentRepository;

public class RentCardMapper {

	EquipmentRepository equipmentRepository;
	
	RentCardMapper (EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}
	
	static RentCardDto rentCardToDto(RentCard card) {
		RentCardDto rentCardDto = new RentCardDto();
		rentCardDto.setId(card.getId());
		rentCardDto.setRentalTime(card.getRentalTime());
		rentCardDto.setActive(card.isActive());
		rentCardDto.setEmployeeId(card.getEmployee().getEmployeeID());
		rentCardDto.setSerialNumber(card.getEquipment().getSerialNumber());
		rentCardDto.setQuantity(card.getQuantity());
		return rentCardDto;
	}
	
	static RentCard dtoToRentCard(RentCardDto cardDto) {
		RentCard card = new RentCard();
		card.setId(cardDto.getId());
		card.setRentalTime(cardDto.getRentalTime());
		card.setActive(cardDto.isActive());
		card.setEmployee(null);
		card.setEquipment(null);
		card.setQuantity(cardDto.getQuantity());
		return card;
	}
	
//	static Equipment getEquipment(String number) {	
//		return equipmentRepository
//				.findBySerialNumber(number)
//				.orElseThrow(IllegalArgumentException::new);
//	}
}
