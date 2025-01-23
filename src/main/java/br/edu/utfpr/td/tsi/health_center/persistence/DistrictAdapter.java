package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.District;

public interface DistrictAdapter extends BaseAdapter<District>, FindAllByFilter<District>{
	public List<District> findAll(String name);
	public boolean existsByName(String name);
}
