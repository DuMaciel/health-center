package br.edu.utfpr.td.tsi.health_center.model.service;

import java.util.List;

public interface FindAllExtension<E> {
	public List<E> findAll(String name);
}
