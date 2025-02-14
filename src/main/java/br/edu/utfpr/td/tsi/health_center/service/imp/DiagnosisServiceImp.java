package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.DiagnosisAdapter;
import br.edu.utfpr.td.tsi.health_center.service.DiagnosisService;

@Service
public class DiagnosisServiceImp implements DiagnosisService {
	
	@Autowired
	private DiagnosisAdapter diagnosisAdapter;
	
	@Override
	public Diagnosis find(String id) {
		return diagnosisAdapter.find(id);
	}

	@Override
	public List<Diagnosis> findAllByFilter(Filter filter) {
		return diagnosisAdapter.findAllByFilter(filter);
	}
}
