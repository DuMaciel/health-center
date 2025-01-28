package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.model.dto.DiagnosisDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.DiagnosisIndexer;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.SolrAdapter;

@Component
@Profile("solr")
public class DiagnosisSolrIndexer implements DiagnosisIndexer {

	@Autowired
	private SolrAdapter solrAdapter;
	
	final String type = "diagnosis";
	
	public void save(Diagnosis diagnosis) {
		DiagnosisDTO diagnosisDTO = new DiagnosisDTO(diagnosis);
		Map<String, Object> fields = Filter.getFields(diagnosisDTO.getClass(), diagnosisDTO);
		solrAdapter.add(type, diagnosis.getId(), fields);
	}
	
	public void save(List<Diagnosis> diagnostics) {
		Map<String, Map<String, Object>> entities = new HashMap<String, Map<String, Object>>();
		for (Diagnosis diagnosis : diagnostics) {
			String id = diagnosis.getId();
			DiagnosisDTO diagnosisDTO = new DiagnosisDTO(diagnosis);
			Map<String, Object> fields = Filter.getFields(diagnosisDTO.getClass(), diagnosisDTO);
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
