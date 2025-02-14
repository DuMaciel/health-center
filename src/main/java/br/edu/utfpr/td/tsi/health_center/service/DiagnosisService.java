package br.edu.utfpr.td.tsi.health_center.service;

import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;

public interface DiagnosisService extends FindAllByFilter<Diagnosis> {
	public Diagnosis find(String id);
}
