package br.edu.utfpr.td.tsi.health_center.controller.validation;

import javax.validation.constraints.NotBlank;

public class DistrictAddValidation {
	@NotBlank(message = "O nome não pode estar em branco neh")
	String name;
}
