package br.edu.utfpr.td.tsi.health_center.controller.validation;

import javax.validation.constraints.NotEmpty;

public class DistrictEditValidation {
	@NotEmpty(message = "O id deve ser definido, recarregue a página.")
	String id;
	
	@NotEmpty(message = "O nome não pode estar em branco.")
	String name;
}
