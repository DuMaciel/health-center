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
	@NotEmpty(message = "Selecione um paciente")
	String patientId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
}
