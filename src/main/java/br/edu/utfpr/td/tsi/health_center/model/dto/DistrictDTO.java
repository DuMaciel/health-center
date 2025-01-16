package br.edu.utfpr.td.tsi.health_center.model.dto;

import br.edu.utfpr.td.tsi.health_center.model.District;

public class DistrictDTO {
	String id;
	String name;
	
	public DistrictDTO() {
		
	}
	
	public DistrictDTO(District district) {
		this.id = district.getId();
		this.name = district.getName();
	}
	
	public District toModel() {
		District district = new District();
		district.setId(id);
		district.setName(name);
		return district;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
