package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.PatientMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.PatientMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.PatientRepository;

@Component
@Profile("mongo")
public class PatientMongoAdapter implements PatientAdapter {
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DistrictAdapter districtAdapter;

	@Override
	public void save(Patient patient) {
		PatientMongo patientMongo = PatientMapper.toMongo(patient);
		patientRepository.save(patientMongo);
	}

	@Override
	public Patient find(String id) {
		PatientMongo patientMongo = patientRepository.findById(id).get();
		String districtId = patientMongo.getAddress().getDistrictId();
		District district = districtAdapter.find(districtId);
		return PatientMapper.toDomain(patientMongo, district);
	}
	
	private List<District> findDistricts(List<PatientMongo> patientsMongo){
		List<String> districtsIds = new ArrayList<String>();
		for (PatientMongo patientMongo : patientsMongo) {
			districtsIds.add(patientMongo.getAddress().getDistrictId());
		}
		return districtAdapter.findAllByIds(districtsIds);
	}

	@Override
	public List<Patient> findAll() {
		List<PatientMongo> patientsMongo = patientRepository.findAll();
		List<District> districts = findDistricts(patientsMongo);
		return PatientMapper.toDomainList(patientsMongo, districts);
	}

	@Override
	public List<Patient> findAllByIds(List<String> ids) {
		List<PatientMongo> patientsMongo = (List<PatientMongo>) patientRepository.findAllById(ids);
		List<District> districts = findDistricts(patientsMongo);
		return PatientMapper.toDomainList(patientsMongo, districts);
	}
	
	@Override
	public List<Patient> findAll(String name) {
		List<PatientMongo> patientsMongo = patientRepository.findAllByName(name);
		List<District> districts = findDistricts(patientsMongo);
		return PatientMapper.toDomainList(patientsMongo, districts);
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
	public boolean existsByAddressDistrictId(String districtId) {
		return patientRepository.existsByAddressDistrictId(districtId);
	}
}