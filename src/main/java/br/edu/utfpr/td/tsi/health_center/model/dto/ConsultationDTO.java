package br.edu.utfpr.td.tsi.health_center.model.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

public class ConsultationDTO {

	private String id;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dateTime;
	private String status;
	private String doctorId;
	private String doctorName;
	private String patientId;
	private String patientName;
	
	public ConsultationDTO() {}
	
	public ConsultationDTO(Consultation consultation) {
		this.id = consultation.getId();
		this.dateTime = consultation.getDateTime();
		this.status = consultation.getStatus().getDescription();
		
		Doctor doctor = consultation.getDoctor();
		Patient patient = consultation.getPatient();
		
		this.doctorId = doctor.getId();
		this.doctorName = doctor.getName();
		
		this.patientId = patient.getId();
		this.patientName = patient.getName();		
	}
	
	public Consultation toModel() {
		Consultation consultation = new Consultation();
		consultation.setId(this.id);
		consultation.setDateTime(this.dateTime);
		
		Doctor doctor = new Doctor();
		doctor.setId(this.doctorId);
		doctor.setName(this.doctorName);
		
		Patient patient = new Patient();
		patient.setId(this.patientId);
		patient.setName(this.patientName);
		
		consultation.setDoctor(doctor);
		consultation.setPatient(patient);
		
		return consultation;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	
	
}
