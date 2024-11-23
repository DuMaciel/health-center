package br.edu.utfpr.td.tsi.health_center.model.service;

import java.util.List;

import org.springframework.lang.Nullable;

public interface FindAllExtension<E> {
	public List<E> findAll(@Nullable String name);
}
