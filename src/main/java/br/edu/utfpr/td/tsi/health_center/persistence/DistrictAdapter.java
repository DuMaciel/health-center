package br.edu.utfpr.td.tsi.health_center.persistence;

import br.edu.utfpr.td.tsi.health_center.model.District;

public interface DistrictAdapter extends BaseAdapter<District>, FindAllByNameExtension<District> {
	public boolean existsByName(String name);
}
