package jp.eightbit.curriculums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "property")
public class Property {
	public Property() {}
	
	public Property(int propertyId) {
		this.propertyId = propertyId;
	}
	
	@Id
	@Column(name = "id")
	@NotNull
	private int propertyId;
	@Column(name = "name")
	@NotNull
	private String name;

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
