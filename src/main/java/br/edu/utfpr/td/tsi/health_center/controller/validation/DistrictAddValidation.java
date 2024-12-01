package br.edu.utfpr.td.tsi.health_center.controller.validation;

import javax.validation.constraints.NotEmpty;

public class DistrictAddValidation {
	@NotEmpty(message = "O nome não pode estar em branco.")
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
