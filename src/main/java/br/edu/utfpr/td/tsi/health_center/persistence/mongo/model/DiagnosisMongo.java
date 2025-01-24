package br.edu.utfpr.td.tsi.health_center.persistence.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DiagnosisMongo {
	
	@Id
	private String id;
	private String detail;
	private String consultationId;
	
	public DiagnosisMongo() {}

	public DiagnosisMongo(String id, String detail, String consultationId) {
		super();
		this.id = id;
		this.detail = detail;
		this.consultationId = consultationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}
	
}
