package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DoctorRepository;
import br.edu.utfpr.td.tsi.health_center.service.DoctorService;

@Service
public class DoctorServiceImp implements DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public void add(Doctor doctor) {
		if(doctorRepository.existsByCpf(doctor.getCpf())) {
			throw new RuntimeException("Um médico com esse cpf já existe");
		} else if (doctorRepository.existsByCrm(doctor.getCrm())) {
			throw new RuntimeException("Um médico com esse crm já existe");
		}
		doctorRepository.save(doctor);
	}
	
	@Override
	public void edit(Doctor doctor) {		
		if(doctorRepository.existsByCpf(doctor.getCpf()) || doctorRepository.existsByCrm(doctor.getCrm())){
			Doctor doctorSaved = doctorRepository.findById(doctor.getId()).get();
			if(!doctorSaved.getCpf().replaceAll("[^0-9]", "").equals(doctor.getCpf().replaceAll("[^0-9]", ""))) {
				throw new RuntimeException("Um médico com esse cpf já existe");
			} else if (!doctorSaved.getCrm().replaceAll("[^0-9]", "").equals(doctor.getCrm().replaceAll("[^0-9]", "")) ) {
				throw new RuntimeException("Um médico com esse crm já existe");
			}
		}
		doctorRepository.save(doctor);
	}
	
	@Override
	public Doctor find(String id) {
		return doctorRepository.findById(id).get();
	}
	
	@Override
	public void delete(String id) {
		// TODO Implementar lógica para validar se o paciente tem consulta
		doctorRepository.deleteById(id);
	}
	
	@Override
	public List<Doctor> findAll(String name) {
		if(name != null && !name.equals("")) {
			return doctorRepository.findAllByName(name);
		}
		return doctorRepository.findAll();
	}
}
