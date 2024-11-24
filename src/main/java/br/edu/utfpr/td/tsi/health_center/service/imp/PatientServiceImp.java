package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.PatientRepository;
import br.edu.utfpr.td.tsi.health_center.service.PatientService;

@Service
public class PatientServiceImp implements PatientService {
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void add(Patient patient) {
		if(patientRepository.existsByCpf(patient.getCpf())) {
			throw new RuntimeException("Um paciente com esse cpf já existe");
		}
		patientRepository.save(patient);
	}

	@Override
	public void edit(Patient patient) {
		if(patientRepository.existsByCpf(patient.getCpf())){
			Patient patientSaved = patientRepository.findById(patient.getId()).get();
			if(!patientSaved.getCpf().replaceAll("[^0-9]", "").equals(patient.getCpf().replaceAll("[^0-9]", ""))) {
				throw new RuntimeException("Um paciente com esse cpf já existe");
			}
		}
		patientRepository.save(patient);
	}

	@Override
	public Patient find(String id) {
		return patientRepository.findById(id).get();
	}

	@Override
	public void delete(String id) {
		// TODO Implementar lógica para validar se o paciente tem consulta
		patientRepository.deleteById(id);
	}

	@Override
	public List<Patient> findAll(String name) {
		if(name != null && !name.equals("")) {
			return patientRepository.findAllByName(name);
		}
		return patientRepository.findAll();
	}
}
