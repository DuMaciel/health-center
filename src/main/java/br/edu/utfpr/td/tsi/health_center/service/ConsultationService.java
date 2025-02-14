package br.edu.utfpr.td.tsi.health_center.service;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;

public interface ConsultationService extends BaseService<Consultation>, FindAllByFilter<Consultation> {
	public List<Consultation> findAll(String patientId, String DoctorId);
	public void completeConsultation(Diagnosis diagnosis);
	public void cancelConsultation(String consultationId);
}
