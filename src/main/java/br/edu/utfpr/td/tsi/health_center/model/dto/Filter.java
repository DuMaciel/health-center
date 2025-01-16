package br.edu.utfpr.td.tsi.health_center.model.dto;

public class Filter {
	private String field;
	private String term;
	
	public Filter(String field, String term) {
		super();
		this.field = field;
		this.term = term;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}
