package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.DoctorMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DistrictMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DoctorMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DistrictRepository;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DoctorRepository;

public class DoctorMongoAdapter implements DoctorAdapter{
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public void save(Doctor doctor) {
		DoctorMongo doctorMongo = DoctorMapper.toMongo(doctor);
		doctorRepository.save(doctorMongo);
	}

	@Override
	public Doctor find(String id) {
		DoctorMongo doctorMongo = doctorRepository.findById(id).get();
		DistrictMongo districtMongo = districtRepository.findById(doctorMongo.getAddress().getId()).get();
		return DoctorMapper.toDomain(doctorMongo, districtMongo);
	}

	@Override
	public List<Doctor> findAll() {
		List<DoctorMongo> doctorsMongo = doctorRepository.findAll();
		List<DistrictMongo> districtsMongo = districtRepository.findAll();
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
		List<DistrictMongo> districtsMongo = districtRepository.findAll();
		return DoctorMapper.toDomainList(doctorMongo, districtsMongo);
	}
}
