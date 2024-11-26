package br.edu.utfpr.td.tsi.health_center.persistence.mongo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import br.edu.utfpr.td.tsi.health_center.model.ConsultationStatus;

@Document("consultation")
public class ConsultationMongo {
	
	@Id
	private String id;
	private String doctorId;
	private String patientId;
	private LocalDateTime dateTime;
	private ConsultationStatus status;
	
	public ConsultationMongo() {
		
	}
	
	public ConsultationMongo(String id, String doctorId, String patientId, LocalDateTime dateTime,
			ConsultationStatus status) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.dateTime = dateTime;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public ConsultationStatus getStatus() {
		return status;
	}

	public void setStatus(ConsultationStatus status) {
		this.status = status;
	}
	
	
}
