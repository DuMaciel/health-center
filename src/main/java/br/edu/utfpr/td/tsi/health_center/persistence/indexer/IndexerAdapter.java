package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

public interface IndexerAdapter {
	public void save(String type, String id, Map<String, Object> fields);
	public void save(String type, Map<String, Map<String, Object>> entities);
	public void delete(String type, String id);
	public List<String> getIds(String type);
	public List<String> searchIds(String type, Filter filter);
	public List<String> searchIdsByFieldValues(String type, String field, List<String> values);
}
