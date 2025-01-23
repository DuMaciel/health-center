package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

public interface FindAllByFilter<E> {
	public List<E> findAllByFilter(Filter filter);
}
