package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.DoctorMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DoctorMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DoctorRepository;

@Component
public class DoctorMongoAdapter implements DoctorAdapter{
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private DistrictAdapter districtAdapter;

	@Override
	public void save(Doctor doctor) {
		DoctorMongo doctorMongo = DoctorMapper.toMongo(doctor);
		doctorRepository.save(doctorMongo);
	}

	@Override
	public Doctor find(String id) {
		DoctorMongo doctorMongo = doctorRepository.findById(id).get();
		String districtId = doctorMongo.getAddress().getDistrictId();
		District district = districtAdapter.find(districtId);
		return DoctorMapper.toDomain(doctorMongo, district);
	}

	@Override
	public List<Doctor> findAll() {
		List<DoctorMongo> doctorsMongo = doctorRepository.findAll();
		List<District> districtsMongo = districtAdapter.findAll();
		return DoctorMapper.toDomainList(doctorsMongo, districtsMongo);
	}

	@Override
	public void delete(String id) {
		doctorRepository.deleteById(id);
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return doctorRepository.existsByCpf(cpf);
	}
	
	@Override
	public boolean existsByCrm(String crm) {
		return doctorRepository.existsByCrm(crm);
	}
	
	@Override
	public List<Doctor> findAllByName(String name) {
		List<DoctorMongo> doctorMongo = doctorRepository.findAllByName(name);
		List<District> districtsMongo = districtAdapter.findAll();
		return DoctorMapper.toDomainList(doctorMongo, districtsMongo);
	}
}
