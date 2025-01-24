package br.edu.utfpr.td.tsi.health_center.model;

public class Diagnosis {
	
	private String id;
	private String details;
	private Consultation consultation;
	
	public Diagnosis() {}
	
	public Diagnosis(String id, String details, Consultation consultation) {
		super();
		this.id = id;
		this.details = details;
		this.consultation = consultation;
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

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	
	
	
}
