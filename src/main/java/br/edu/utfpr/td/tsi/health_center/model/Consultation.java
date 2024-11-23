package br.edu.utfpr.td.tsi.health_center.model;

import java.time.LocalDateTime;

public class Consultation {

	private int id; 
	private Doctor doctor;
	private Patient patient;
	private LocalDateTime dateTime;
	private ConsultationStatus status;
	
	public Consultation(int id, Doctor doctor, Patient patient, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.patient = patient;
		this.dateTime = dateTime;
		this.status = ConsultationStatus.SCHEDULED;
	}
	
	public int getId() {
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
	
	
	
}
