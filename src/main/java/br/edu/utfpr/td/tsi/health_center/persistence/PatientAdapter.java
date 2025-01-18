package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;

public interface PatientAdapter extends BaseAdapter<Patient>{
	public List<Patient> findAll(String name);
	public List<Patient> findAll(Filter filter);
	public boolean existsByCpf(String cpf);
	public boolean existsByAddressDistrictId(String districtId);
	public List<Patient> findAllByIdsNotIn(List<String> ids);
}
