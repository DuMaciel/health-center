package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

public interface FindAllByNameExtension<E> {
	public List<E> findAllByName(String name);
}
