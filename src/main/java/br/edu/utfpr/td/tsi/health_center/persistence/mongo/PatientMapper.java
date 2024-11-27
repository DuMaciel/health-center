package br.edu.utfpr.td.tsi.health_center.persistence.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.PatientMongo;

public class PatientMapper {
	static public PatientMongo toMongo(Patient patient) {
		PatientMongo patientMongo = new PatientMongo();
		patientMongo.setId(patient.getId());
		patientMongo.setName(patient.getName());
		patientMongo.setCpf(patient.getCpf());
		patientMongo.setAddress(AddressMapper.toMongo(patient.getAddress()));
		return patientMongo;
	}
	
	static public Patient toDomain(PatientMongo patientMongo, District district) {
		Patient patient = new Patient();
		patient.setId(patientMongo.getId());
		patient.setName(patientMongo.getName());
		patient.setCpf(patientMongo.getCpf());
		patient.setAddress(AddressMapper.toDomain(patientMongo.getAddress(), district));
		return patient;
	}
	
	static public List<PatientMongo> toMongoList(List<Patient> patients) {
		List<PatientMongo> patientsMongo = new ArrayList<PatientMongo>();
		for (Patient patient : patients) {
			patientsMongo.add(toMongo(patient));
		}
		return patientsMongo;
	}
	
	static public List<Patient> toDomainList(List<PatientMongo> patientsMongo, List<District> districts) {
		List<Patient> patients = new ArrayList<Patient>();
		Map<String, District> districtMap = new HashMap<String, District>();
		for (District district : districts) {
			districtMap.put(district.getId(), district);
		}
		for (PatientMongo patientMongo : patientsMongo) {
			District district = districtMap.get(patientMongo.getAddress().getDistrictId());
			patients.add(toDomain(patientMongo, district));
		}
		return patients;
	}
}
