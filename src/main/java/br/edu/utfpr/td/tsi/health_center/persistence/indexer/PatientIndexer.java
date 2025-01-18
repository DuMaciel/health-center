package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.dto.PatientDTO;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.SolrAdapter;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

@Component
public class PatientIndexer {
	@Autowired
	private SolrAdapter solrAdapter;
	
	final String type = "patient";
	
	public void add(Patient patient) {
		PatientDTO patientDTO = new PatientDTO(patient);
		Map<String, Object> fields = Filter.getFields(patientDTO.getClass(), patientDTO);
		solrAdapter.save(type, patient.getId(), fields);
	}
	
	public void addAll(List<Patient> patients) {
		Map<String, Map<String, Object>> entities = new HashMap<String, Map<String, Object>>();
		for (Patient patient : patients) {
			String id = patient.getId();
			PatientDTO patientDTO = new PatientDTO(patient);
			Map<String, Object> fields = Filter.getFields(patientDTO.getClass(), patientDTO);
			entities.put(id, fields);
		}
		solrAdapter.saveAll(type, entities);
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
