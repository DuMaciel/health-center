package br.edu.utfpr.td.tsi.health_center.controller.validation;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConsultationEditValidation {
	@NotEmpty(message = "O id deve ser definido, recarregue a página.")
	String id;
	@NotNull(message = "A data é obrigatório")
	LocalDateTime dateTime;
	@NotEmpty(message = "Selecione um médico")
	String doctorId;
	@NotEmpty(message = "Selecione um médico")
	String doctorName;
	@NotEmpty(message = "Selecione um paciente")
	String patientId;
	@NotEmpty(message = "Selecione um paciente")
	String patientName;
}
