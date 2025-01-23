package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import java.util.List;

public interface FindAllByIdNotInExtension<E> {
	public List<E> findAllByIdNotIn(List<String> ids);
}
