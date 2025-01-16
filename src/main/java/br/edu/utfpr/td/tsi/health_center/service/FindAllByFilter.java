package br.edu.utfpr.td.tsi.health_center.service;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

public interface FindAllByFilter<E> {
	public List<E> findAll(Filter filter);
}
