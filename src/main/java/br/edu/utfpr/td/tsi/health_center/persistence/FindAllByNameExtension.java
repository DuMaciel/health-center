package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;

public interface FindAllByNameExtension<E> {
	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	List<E> findAllByName(String search);
}
