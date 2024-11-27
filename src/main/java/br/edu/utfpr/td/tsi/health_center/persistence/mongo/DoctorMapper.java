package br.edu.utfpr.td.tsi.health_center.persistence.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DoctorMongo;

public class DoctorMapper {
	static public DoctorMongo toMongo(Doctor doctor) {
		DoctorMongo doctorMongo = new DoctorMongo();
		doctorMongo.setId(doctor.getId());
		doctorMongo.setName(doctor.getName());
		doctorMongo.setCpf(doctor.getCpf());
		doctorMongo.setCrm(doctor.getCrm());
		doctorMongo.setAddress(AddressMapper.toMongo(doctor.getAddress()));
		return doctorMongo;
	}
	
	static public Doctor toDomain(DoctorMongo doctorMongo, District district) {
		Doctor doctor = new Doctor();
		doctor.setId(doctorMongo.getId());
		doctor.setName(doctorMongo.getName());
		doctor.setCpf(doctorMongo.getCpf());
		doctor.setCrm(doctorMongo.getCrm());
		doctor.setAddress(AddressMapper.toDomain(doctorMongo.getAddress(), district));
		return doctor;
	}
	
	static public List<DoctorMongo> toMongoList(List<Doctor> doctors) {
		List<DoctorMongo> doctorsMongo = new ArrayList<DoctorMongo>();
		for (Doctor doctor : doctors) {
			doctorsMongo.add(toMongo(doctor));
		}
		return doctorsMongo;
	}
	
	static public List<Doctor> toDomainList(List<DoctorMongo> doctorsMongo, List<District> districts) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		Map<String, District> districtMap = new HashMap<String, District>();
		for (District district : districts) {
			districtMap.put(district.getId(), district);
		}
		for (DoctorMongo doctorMongo : doctorsMongo) {
			District district = districtMap.get(doctorMongo.getAddress().getDistrictId());
			doctors.add(toDomain(doctorMongo, district));
		}
		return doctors;
	}
}
