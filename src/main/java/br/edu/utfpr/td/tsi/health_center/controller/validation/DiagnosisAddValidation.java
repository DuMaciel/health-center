package br.edu.utfpr.td.tsi.health_center.controller.validation;

import javax.validation.constraints.NotNull;

public class DiagnosisAddValidation {

	@NotNull(message = "Diagnóstico é obrigatório")
	private String details;
	@NotNull(message = "Consulta é obrigatório")
	private String consultationId;
	private String patientName;
	private String doctorName;
//	private String status;
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getConsultationId() {
		return consultationId;
	}
	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	
}
