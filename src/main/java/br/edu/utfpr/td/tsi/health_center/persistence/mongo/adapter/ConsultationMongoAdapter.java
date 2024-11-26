package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.ConsultationMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.ConsultationMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.ConsultationRepository;

@Component
public class ConsultationMongoAdapter implements ConsultationAdapter {
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private DoctorAdapter doctorAdapter;
	
	@Autowired
	private PatientAdapter patientAdapter;

	@Override
	public void save(Consultation consultation) {
		ConsultationMongo consultationMongo = ConsultationMapper.toMongo(consultation);
		consultationRepository.save(consultationMongo);
	}

	@Override
	public Consultation find(String id) {
		ConsultationMongo consultationMongo = consultationRepository.findById(id).get();
		Doctor doctor = doctorAdapter.find(consultationMongo.getDoctorId());
		Patient patient = patientAdapter.find(consultationMongo.getPatientId());
		return ConsultationMapper.toDomain(consultationMongo, doctor, patient);
	}

	@Override
	public List<Consultation> findAll() {
		List<ConsultationMongo> consultationMongo = consultationRepository.findAll();
		List<Doctor> doctors = doctorAdapter.findAll();
		List<Patient> patients = patientAdapter.findAll();
		return ConsultationMapper.toDomainList(consultationMongo, doctors, patients);
	}

	@Override
	public void delete(String id) {
		consultationRepository.deleteById(id);
	}

}
