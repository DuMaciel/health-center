package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.ConsultationStatus;

public interface ConsultationAdapter extends BaseAdapter<Consultation>, FindAllByFilter<Consultation>{
	public boolean existsByPatientIdAndStatus(String patientId, ConsultationStatus status);
	
	public boolean existsByPatientId(String patientId);
	
	public boolean existsByDoctorId(String doctorId);
	
	public List<Consultation> findAll(String patientId, String doctorId);
	
	public List<Consultation> findAll(String patientId, ConsultationStatus status);
}
