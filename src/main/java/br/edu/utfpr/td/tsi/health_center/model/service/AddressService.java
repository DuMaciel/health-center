package br.edu.utfpr.td.tsi.health_center.model.service;

import br.edu.utfpr.td.tsi.health_center.model.Address;

public interface AddressService {
	public void add(Address address);
	
	public void edit(Address address);
	
	public Address find(String id);
	
	public void delete(String id);
}
