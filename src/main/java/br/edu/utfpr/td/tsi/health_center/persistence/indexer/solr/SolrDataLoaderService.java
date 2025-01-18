package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.PatientIndexer;

@Service
public class SolrDataLoaderService {
	@Autowired
    private PatientIndexer patientIndexer;
	
	@Autowired
	private PatientAdapter patientAdapter;
	
	public void loadDataToSolr() throws Exception {
		List<Patient> patients = patientAdapter.findAll();
		patientIndexer.addAll(patients);
	}
}
