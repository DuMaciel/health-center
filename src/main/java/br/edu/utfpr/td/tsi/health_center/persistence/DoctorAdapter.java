package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;

public interface DoctorAdapter extends BaseAdapter<Doctor>{
	public List<Doctor> findAll(String name);
	public boolean existsByCpf(String cpf);
	public boolean existsByCrm(String crm);
	public boolean existsByAddressDistrictId(String districtId);
}
