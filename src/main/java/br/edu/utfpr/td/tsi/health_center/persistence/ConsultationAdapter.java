package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;

public interface ConsultationAdapter extends BaseAdapter<Consultation>{
	public List<Consultation> findAllByPatientIdAndDoctorId(String patientId, String doctorId);
}
