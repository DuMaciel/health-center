package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.ConsultationStatus;

public interface ConsultationAdapter extends BaseAdapter<Consultation>{
	public boolean existsByPatientIdAndStatus(String patientId, ConsultationStatus status);
	
	public List<Consultation> findAll(String patientId, String doctorId);
}
