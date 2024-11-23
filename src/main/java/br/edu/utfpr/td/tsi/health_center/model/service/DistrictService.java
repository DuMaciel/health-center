package br.edu.utfpr.td.tsi.health_center.model.service;

import br.edu.utfpr.td.tsi.health_center.model.District;

public interface DistrictService {
	public void add(District district);
	
	public void edit(District district);
	
	public District find(String id);
	
	public District findAll(String... name);
	
	public void delete(String id);
}
