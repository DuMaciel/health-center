package br.edu.utfpr.td.tsi.health_center.model;

import java.time.LocalDateTime;

public class Consultation implements BaseModel {
	private String id; 
	private Doctor doctor;
	private Patient patient;
	private LocalDateTime dateTime;
	private ConsultationStatus status;
	
	public Consultation() {
		
	}
	
	public Consultation(String id, Doctor doctor, Patient patient, LocalDateTime dateTime, ConsultationStatus status) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.patient = patient;
		this.dateTime = dateTime;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public ConsultationStatus getStatus() {
		return status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setStatus(ConsultationStatus status) {
		this.status = status;
	}
	
}
