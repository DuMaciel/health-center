package br.edu.utfpr.td.tsi.health_center.model.service;

public interface BaseService<E> {
	public void add(E entity);
	
	public void edit(E entity);
	
	public E find(String id);
	
	public void delete(String id);
}
