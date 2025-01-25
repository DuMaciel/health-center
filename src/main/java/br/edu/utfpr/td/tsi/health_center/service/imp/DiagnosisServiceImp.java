package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DiagnosisAdapter;
import br.edu.utfpr.td.tsi.health_center.service.DiagnosisService;

@Service
public class DiagnosisServiceImp implements DiagnosisService {
	
	@Autowired
	private DiagnosisAdapter diagnosisAdapter;
	
	@Autowired
	private ConsultationAdapter consultationAdapter;
	
	@Override
	public void add(Diagnosis diagnosis) {
		diagnosisAdapter.save(diagnosis);
	}

	@Override
	public void edit(Diagnosis diagnosis) {
		diagnosisAdapter.save(diagnosis);
	}

	@Override
	public Diagnosis find(String id) {
		return diagnosisAdapter.find(id);
	}

	@Override
	public void delete(String id) {
		if (consultationAdapter.existsByPatientId(id)) {
			throw new RuntimeException("Existe uma consulta vinculado a esse diagnóstico.");
		}
		diagnosisAdapter.delete(id);
	}

	@Override
	public List<Diagnosis> findAll(String name) {
		return diagnosisAdapter.findAll();
	}
}
