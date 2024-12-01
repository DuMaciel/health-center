package br.edu.utfpr.td.tsi.health_center.model;

public enum ConsultationStatus {
	SCHEDULED("Agendada"),
	CANCELED("Cancelado"),
	COMPLETED("Concluído");
	
	 private String description;

	ConsultationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
