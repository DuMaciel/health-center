package br.edu.utfpr.td.tsi.health_center.persistence.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DistrictMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.PatientMongo;

public class PatientMapper {
	static public PatientMongo toMongo(Patient patient) {
		PatientMongo patientMongo = new PatientMongo();
		patientMongo.setId(patient.getId());
		patientMongo.setName(patient.getName());
		return patientMongo;
	}
	
	static public Patient toDomain(PatientMongo patientMongo, DistrictMongo districtMongo) {
		Patient patient = new Patient();
		patient.setId(patientMongo.getId());
		patient.setName(patientMongo.getName());
		patient.setCpf(patientMongo.getCpf());
		
		patient.setAddress(AddressMapper.toDomain(patientMongo.getAddress(), null));
		return patient;
	}
	
	static public List<PatientMongo> toMongoList(List<Patient> patients) {
		List<PatientMongo> patientsMongo = new ArrayList<PatientMongo>();
		for (Patient patient : patients) {
			patientsMongo.add(toMongo(patient));
		}
		return patientsMongo;
	}
	
	static public List<Patient> toDomainList(List<PatientMongo> patientsMongo, List<DistrictMongo> districtsMongo) {
		List<Patient> patients = new ArrayList<Patient>();
		Map<String, DistrictMongo> districtMongoMap = new HashMap<String, DistrictMongo>();
		for (DistrictMongo districtMongo : districtsMongo) {
			districtMongoMap.put(districtMongo.getId(), districtMongo);
		}
		for (PatientMongo patientMongo : patientsMongo) {
			DistrictMongo districtMongo = districtMongoMap.get(patientMongo.getAddress().getDistrictId());
			patients.add(toDomain(patientMongo, districtMongo));
		}
		return patients;
	}
}
