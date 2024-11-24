package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.PatientMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DistrictMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.PatientMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.PatientRepository;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DistrictRepository;

@Component
public class PatientMongoAdapter implements PatientAdapter {
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public void save(Patient patient) {
		PatientMongo patientMongo = PatientMapper.toMongo(patient);
		patientRepository.save(patientMongo);
	}

	@Override
	public Patient find(String id) {
		PatientMongo patientMongo = patientRepository.findById(id).get();
		String districtId = patientMongo.getAddress().getDistrictId();
		DistrictMongo districtMongo = districtRepository.findById(districtId).get();
		return PatientMapper.toDomain(patientMongo, districtMongo);
	}

	@Override
	public List<Patient> findAll() {
		List<PatientMongo> patientMongo = patientRepository.findAll();
		List<DistrictMongo> districtsMongo = districtRepository.findAll();
		return PatientMapper.toDomainList(patientMongo, districtsMongo);
	}

	@Override
	public void delete(String id) {
		patientRepository.deleteById(id);
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return patientRepository.existsByCpf(cpf);
	}
	
	@Override
	public List<Patient> findAllByName(String name) {
		List<PatientMongo> patientMongo = patientRepository.findAllByName(name);
		List<DistrictMongo> districtsMongo = districtRepository.findAll();
		return PatientMapper.toDomainList(patientMongo, districtsMongo);
	}
}