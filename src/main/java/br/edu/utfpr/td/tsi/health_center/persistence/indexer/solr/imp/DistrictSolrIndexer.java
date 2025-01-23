package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.dto.DistrictDTO;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.DistrictIndexer;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.SolrAdapter;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

@Component
@Profile("solr")
public class DistrictSolrIndexer implements DistrictIndexer {
	@Autowired
	private SolrAdapter solrAdapter;
	
	final String type = "district";
	
	public void save(District district) {
		DistrictDTO districtDTO = new DistrictDTO(district);
		Map<String, Object> fields = Filter.getFields(districtDTO.getClass(), districtDTO);
		solrAdapter.add(type, district.getId(), fields);
	}
	
	public void save(List<District> districts) {
		Map<String, Map<String, Object>> entities = new HashMap<String, Map<String, Object>>();
		for (District district : districts) {
			String id = district.getId();
			DistrictDTO districtDTO = new DistrictDTO(district);
			Map<String, Object> fields = Filter.getFields(districtDTO.getClass(), districtDTO);
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
