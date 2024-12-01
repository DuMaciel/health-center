package br.edu.utfpr.td.tsi.health_center.controller.validation;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConsultationAddValidation {
	
	@NotNull(message = "Data é obrigatório")
	LocalDateTime dateTime;
	@NotNull(message = "Selecione um médico")
	String doctorId;
	@NotEmpty(message = "Selecione um médico")
	String doctorName;
	@NotEmpty(message = "Selecione um paciente")
	String patientId;
	@NotEmpty(message = "Selecione um paciente")
	String patientName;
}
