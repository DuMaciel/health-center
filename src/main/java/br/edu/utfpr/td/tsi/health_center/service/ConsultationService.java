package br.edu.utfpr.td.tsi.health_center.service;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;

public interface ConsultationService extends BaseService<Consultation> {
	public List<Consultation> findAll();
}
