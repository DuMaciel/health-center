package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.dto.DoctorDTO;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.DoctorIndexer;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.SolrAdapter;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

@Component
@Profile("solr")
public class DoctorSolrIndexer implements DoctorIndexer {
	@Autowired
	private SolrAdapter solrAdapter;
	
	final String type = "doctor";
	
	public void save(Doctor doctor) {
		DoctorDTO doctorDTO = new DoctorDTO(doctor);
		Map<String, Object> fields = Filter.getFields(doctorDTO.getClass(), doctorDTO);
		solrAdapter.add(type, doctor.getId(), fields);
	}
	
	public void save(List<Doctor> doctors) {
		Map<String, Map<String, Object>> entities = new HashMap<String, Map<String, Object>>();
		for (Doctor doctor : doctors) {
			String id = doctor.getId();
			DoctorDTO doctorDTO = new DoctorDTO(doctor);
			Map<String, Object> fields = Filter.getFields(doctorDTO.getClass(), doctorDTO);
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
