package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

@Component
public class PatientIndexer {
	@Autowired
	private SolrAdapter solrAdapter;
	
	final String type = "patient";
	
	public void add(Patient patient) {
		Map<String, Object> fields = getFields(patient);
		solrAdapter.save(type, patient.getId(), fields);
	}
	
	public List<String> search(String field, String term) {
		String query = String.format("%s:%s*", field, term);
		return solrAdapter.searchIdsByType(type, query);
	}
	
	private Map<String, Object> getFields(Patient patient){
		Address address = patient.getAddress();
		District district = address.getDistrict();
		
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("name", patient.getName());
		fields.put("cpf", patient.getCpf());
		fields.put("postalCode", address.getPostalCode());
		fields.put("street", address.getStreet());
		fields.put("number", address.getNumber());
		fields.put("complement", address.getComplement());
		fields.put("districtName", district.getName());
		
		return fields;
	}
}
