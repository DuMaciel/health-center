package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.dto.BaseDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.model.dto.FilterAnnotation;
import br.edu.utfpr.td.tsi.health_center.model.dto.ModelAnnotation;

@Component
public class IndexerService {
	@Autowired
	private IndexerAdapter indexerAdapter;
	
	public void save(BaseDTO entity) {
		Class<? extends BaseDTO> c = entity.getClass();
		Map<String, Object> fields = Filter.getFields(c, entity);
		indexerAdapter.save(getEntityType(c), entity.getId(), fields);
	}
	
	public void save(List<? extends BaseDTO> entities) {
		if(entities.size() < 1) {
			return;
		}
		Class<? extends BaseDTO> c = entities.getFirst().getClass();
		
		Map<String, Map<String, Object>> entitiesMap = new HashMap<String, Map<String, Object>>();
		for (BaseDTO entity : entities) {
			String id = entity.getId();
			Map<String, Object> fields = Filter.getFields(c, entity);
			entitiesMap.put(id, fields);
		}
		
		indexerAdapter.save(getEntityType(c), entitiesMap);
	};
	
	public void delete(Class<? extends BaseDTO> c, String id) {
		indexerAdapter.delete(getEntityType(c), id);
	}
	
	public List<String> getIds(Class<? extends BaseDTO> c){
		return indexerAdapter.getIds(getEntityType(c));
	}
	
	public List<String> searchIds(Class<? extends BaseDTO> c, Filter filter){
		FilterAnnotation filterAnnotation = Filter.getFilterAnnotation(c, filter.getField());
		Class<? extends BaseDTO> relatedEntity = filterAnnotation.relatedEntity();
		String relatedField = filterAnnotation.relatedField();
		if(!relatedField.isBlank()) {
			Filter filterRelatedEntity = new Filter(relatedField, filter.getTerm());
			List<String> idsRelatedEntity = this.searchIds(relatedEntity, filterRelatedEntity);
			return indexerAdapter.searchIdsByFieldValues(getEntityType(c), filter.getField(), idsRelatedEntity);
		}
		return indexerAdapter.searchIds(getEntityType(c), filter);
	}
	
	private String getEntityType(Class<? extends BaseDTO> c) {
		ModelAnnotation modelAnnotation = c.getAnnotation(ModelAnnotation.class);
		return modelAnnotation.model().getSimpleName();
	}
}
