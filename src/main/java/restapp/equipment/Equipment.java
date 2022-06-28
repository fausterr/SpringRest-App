package restapp.equipment;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique = true)
    private String serialNumber;
	@Column(unique = true)
	private String name;
	private int quantity;
	private String category;
	private boolean available;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean isAvailable) {
		this.available = isAvailable;
	}
	@Override
	public int hashCode() {
		return Objects.hash(category, id, available, name, quantity, serialNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		return Objects.equals(category, other.category) && Objects.equals(id, other.id)
				&& available == other.available && Objects.equals(name, other.name) && quantity == other.quantity
				&& Objects.equals(serialNumber, other.serialNumber);
	}	
}
