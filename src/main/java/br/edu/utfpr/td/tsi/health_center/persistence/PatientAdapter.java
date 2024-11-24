package br.edu.utfpr.td.tsi.health_center.persistence;

import br.edu.utfpr.td.tsi.health_center.model.Patient;

public interface PatientAdapter extends BaseAdapter<Patient>, FindAllByNameExtension<Patient> {
	public boolean existsByCpf(String cpf);
}
