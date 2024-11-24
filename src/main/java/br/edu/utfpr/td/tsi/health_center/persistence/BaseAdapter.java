package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

public interface BaseAdapter<E> {
	public void save(E entity);
	
	public E find(String id);
	
	public List<E> findAll();
	
	public void delete(String id);
}