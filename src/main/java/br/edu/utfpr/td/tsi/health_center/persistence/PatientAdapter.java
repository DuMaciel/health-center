package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Patient;

public interface PatientAdapter extends BaseAdapter<Patient>, FindAllByFilter<Patient>{
	public List<Patient> findAll(String name);
	public boolean existsByCpf(String cpf);
	public boolean existsByAddressDistrictId(String districtId);
}
