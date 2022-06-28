package restapp.equipment;

public class EquipmentMapper {

	public static EquipmentDto equipmentToDto(Equipment equipment) {
		EquipmentDto equipmentDto = new EquipmentDto();
		equipmentDto.setId(equipment.getId());
		equipmentDto.setSerialNumber(equipment.getSerialNumber());
		equipmentDto.setName(equipment.getName());
		equipmentDto.setQuantity(equipment.getQuantity());
		equipmentDto.setCategory(equipment.getCategory());
		equipmentDto.setAvailable(equipment.isAvailable());
		return equipmentDto;
	}
	
	public static Equipment dtoToEquipment(EquipmentDto equipmentDto) {
		Equipment equipment = new Equipment();
		equipment.setId(equipmentDto.getId());
		equipment.setSerialNumber(equipmentDto.getSerialNumber());
		equipment.setName(equipmentDto.getName());
		equipment.setQuantity(equipmentDto.getQuantity());
		equipment.setCategory(equipmentDto.getCategory());
		equipment.setAvailable(equipmentDto.isAvailable());
		return equipment;
	}
}
