package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.service.PatientService;

@Service
public class PatientServiceImp implements PatientService {
	@Autowired
	private PatientAdapter patientAdapter;
	
	@Autowired
	private ConsultationAdapter consultationAdapter;

	@Override
	public void add(Patient patient) {
		if(patientAdapter.existsByCpf(patient.getCpf())) {
			throw new RuntimeException("Um paciente com esse cpf já existe");
		}
		patientAdapter.save(patient);
	}

	@Override
	public void edit(Patient patient) {
		if(patientAdapter.existsByCpf(patient.getCpf())){
			Patient patientSaved = patientAdapter.find(patient.getId());
			if(!patientSaved.getCpf().replaceAll("[^0-9]", "").equals(patient.getCpf().replaceAll("[^0-9]", ""))) {
				throw new RuntimeException("Um paciente com esse cpf já existe");
			}
		}
		patientAdapter.save(patient);
	}

	@Override
	public Patient find(String id) {
		return patientAdapter.find(id);
	}

	@Override
	public void delete(String id) {
		if (consultationAdapter.existsByPatientId(id)) {
			throw new RuntimeException("Existe uma consulta vinculado a esse paciente.");
		}
		patientAdapter.delete(id);
	}

	@Override
	public List<Patient> findAll(String name) {
		if(name != null && !name.equals("")) {
			return patientAdapter.findAllByName(name);
		}
		return patientAdapter.findAll();
	}
}
