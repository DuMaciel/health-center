package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

public interface BaseIndexer<E> {
	public void save(E entity);
	public void save(List<E> entities);
	public void delete(String id);
	public List<String> getIds();
	public List<String> searchIds(Filter filter);
}
