package br.edu.utfpr.td.tsi.health_center.persistence.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("diagnosis")
public class DiagnosisMongo {
	
	@Id
	private String id;
	private String details;
	private String consultationId;
	
	public DiagnosisMongo() {}

	public DiagnosisMongo(String id, String details, String consultationId) {
		super();
		this.id = id;
		this.details = details;
		this.consultationId = consultationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
}
