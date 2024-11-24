package br.edu.utfpr.td.tsi.health_center.persistence;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;

public interface DoctorAdapter extends BaseAdapter<Doctor>, FindAllByNameExtension<Doctor>{
	public boolean existsByCpf(String cpf);
	public boolean existsByCrm(String crm);
}
