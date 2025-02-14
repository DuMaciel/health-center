package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.service.DoctorService;

@Service
public class DoctorServiceImp implements DoctorService {
	
	@Autowired
	private DoctorAdapter doctorAdapter;
	
	@Autowired
	private ConsultationAdapter consultationAdapter;
	
	@Override
	public void add(Doctor doctor) {
		if(doctorAdapter.existsByCpf(doctor.getCpf())) {
			throw new RuntimeException("Um médico com esse cpf já existe");
		} else if (doctorAdapter.existsByCrm(doctor.getCrm())) {
			throw new RuntimeException("Um médico com esse crm já existe");
		}
		doctorAdapter.save(doctor);
	}
	
	@Override
	public void edit(Doctor doctor) {		
		if(doctorAdapter.existsByCpf(doctor.getCpf()) || doctorAdapter.existsByCrm(doctor.getCrm())){
			Doctor doctorSaved = doctorAdapter.find(doctor.getId());
			if(!doctorSaved.getCpf().replaceAll("[^0-9]", "").equals(doctor.getCpf().replaceAll("[^0-9]", ""))) {
				throw new RuntimeException("Um médico com esse cpf já existe");
			} else if (!doctorSaved.getCrm().replaceAll("[^0-9]", "").equals(doctor.getCrm().replaceAll("[^0-9]", "")) ) {
				throw new RuntimeException("Um médico com esse crm já existe");
			}
		}
		doctorAdapter.save(doctor);
	}
	
	@Override
	public Doctor find(String id) {
		return doctorAdapter.find(id);
	}
	
	@Override
	public void delete(String id) {
		if (consultationAdapter.existsByDoctorId(id)) {
			throw new RuntimeException("Existe uma consulta vinculada a esse médico.");
		}
		doctorAdapter.delete(id);
	}
	
	@Override
	public List<Doctor> findAll(String name) {
		if(name != null && !name.equals("")) {
			return doctorAdapter.findAll(name);
		}
		return doctorAdapter.findAll();
	}

	@Override
	public List<Doctor> findAllByFilter(Filter filter) {
		return doctorAdapter.findAllByFilter(filter);
	}
}
