package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.dto.PatientDTO;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.PatientIndexer;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.SolrAdapter;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

@Component
@Profile("solr")
public class PatientSolrIndexer implements PatientIndexer {
	@Autowired
	private SolrAdapter solrAdapter;
	
	final String type = "patient";
	
	public void save(Patient patient) {
		PatientDTO patientDTO = new PatientDTO(patient);
		Map<String, Object> fields = Filter.getFields(patientDTO.getClass(), patientDTO);
		solrAdapter.add(type, patient.getId(), fields);
	}
	
	public void save(List<Patient> patients) {
		Map<String, Map<String, Object>> entities = new HashMap<String, Map<String, Object>>();
		for (Patient patient : patients) {
			String id = patient.getId();
			PatientDTO patientDTO = new PatientDTO(patient);
			Map<String, Object> fields = Filter.getFields(patientDTO.getClass(), patientDTO);
			entities.put(id, fields);
		}
		solrAdapter.add(type, entities);
	}
	
	public void delete(String id) {
		solrAdapter.delete(type, id);
	}
	
	public List<String> getIds(){
		return solrAdapter.getIds(type);
	}
	
	public List<String> searchIds(Filter filter) {
		String field = filter.getField();
		String term = filter.getTerm();
		String query = String.format("%s:%s*", field, term);
		return solrAdapter.searchIds(type, query);
	}
}
