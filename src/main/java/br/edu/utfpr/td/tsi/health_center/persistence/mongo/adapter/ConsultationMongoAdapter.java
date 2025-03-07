package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.ConsultationStatus;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.dto.ConsultationDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.IndexerService;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.ConsultationMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.ConsultationMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.ConsultationRepository;

@Component
@Profile("mongo")
public class ConsultationMongoAdapter implements ConsultationAdapter {
	@Autowired
	private ConsultationRepository consultationRepository;

	@Autowired
	private DoctorAdapter doctorAdapter;

	@Autowired
	private PatientAdapter patientAdapter;
	
	@Autowired
	private IndexerService indexerService;

	@Override
	public void save(Consultation consultation) {
		ConsultationMongo consultationMongo = ConsultationMapper.toMongo(consultation);
		consultationMongo = consultationRepository.save(consultationMongo);
		consultation.setId(consultationMongo.getId());
		indexerService.save(new ConsultationDTO(consultation));
	}

	@Override
	public Consultation find(String id) {
		ConsultationMongo consultationMongo = consultationRepository.findById(id).get();
		Doctor doctor = doctorAdapter.find(consultationMongo.getDoctorId());
		Patient patient = patientAdapter.find(consultationMongo.getPatientId());
		return ConsultationMapper.toDomain(consultationMongo, doctor, patient);
	}
	
	private List<Doctor> findDoctors(List<ConsultationMongo> consultationsMongo){
		List<String> doctorsIds = new ArrayList<String>();
		for (ConsultationMongo consultationMongo : consultationsMongo) {
			doctorsIds.add(consultationMongo.getDoctorId());
		}
		return doctorAdapter.findAllByIds(doctorsIds);
	}
	
	private List<Patient> findPatients(List<ConsultationMongo> consultationsMongo){
		List<String> patientsIds = new ArrayList<String>();
		for (ConsultationMongo consultationMongo : consultationsMongo) {
			patientsIds.add(consultationMongo.getPatientId());
		}
		return patientAdapter.findAllByIds(patientsIds);
	}

	@Override
	public List<Consultation> findAll() {
		List<ConsultationMongo> consultationsMongo = consultationRepository.findAll();
		List<Doctor> doctors = findDoctors(consultationsMongo);
		List<Patient> patients = findPatients(consultationsMongo);
		return ConsultationMapper.toDomainList(consultationsMongo, doctors, patients);
	}

	@Override
	public List<Consultation> findAllByIds(List<String> ids) {
		List<ConsultationMongo> consultationsMongo = (List<ConsultationMongo>) consultationRepository.findAllById(ids);
		List<Doctor> doctors = findDoctors(consultationsMongo);
		List<Patient> patients = findPatients(consultationsMongo);
		return ConsultationMapper.toDomainList(consultationsMongo, doctors, patients);
	}
	
	@Override
	public List<Consultation> findAllByIdsNotIn(List<String> ids) {
		List<ConsultationMongo> consultationsMongo = (List<ConsultationMongo>) consultationRepository.findAllByIdNotIn(ids);
		List<Doctor> doctors = findDoctors(consultationsMongo);
		List<Patient> patients = findPatients(consultationsMongo);
		return ConsultationMapper.toDomainList(consultationsMongo, doctors, patients);
	}

	@Override
	public List<Consultation> findAll(String patientId, String doctorId) {
		List<ConsultationMongo> consultationsMongo;
		if(Strings.isEmpty(patientId) && Strings.isEmpty(doctorId)) {
			consultationsMongo = consultationRepository.findAll();
		} else if(Strings.isEmpty(patientId)) {
			consultationsMongo = consultationRepository.findAllByDoctorId(doctorId);
		} else if(Strings.isEmpty(doctorId)) {
			consultationsMongo = consultationRepository.findAllByPatientId(patientId);
		} else {
			consultationsMongo = consultationRepository.findAllByPatientIdAndDoctorId(patientId, doctorId);
		}
		List<Doctor> doctors = findDoctors(consultationsMongo);
		List<Patient> patients = findPatients(consultationsMongo);
		return ConsultationMapper.toDomainList(consultationsMongo, doctors, patients);
	}
	
	@Override
	public List<Consultation> findAll(String patientId, ConsultationStatus status) {
		List<ConsultationMongo> consultationsMongo = consultationRepository.findAllByPatientIdAndStatus(patientId, status);
		List<Doctor> doctors = findDoctors(consultationsMongo);
		List<Patient> patients = findPatients(consultationsMongo);
		return ConsultationMapper.toDomainList(consultationsMongo, doctors, patients);
	}
	
	@Override
	public List<Consultation> findAllByFilter(Filter filter) {
		List<String> consultationsIds = indexerService.searchIds(ConsultationDTO.class, filter);
		return findAllByIds(consultationsIds);
	}
	
	@Override
	public void delete(String id) {
		consultationRepository.deleteById(id);
		indexerService.delete(ConsultationDTO.class, id);
	}
	
	@Override
	public boolean existsByPatientIdAndStatus(String patientId, ConsultationStatus status) {
		return consultationRepository.existsByPatientIdAndStatus(patientId, status);
	}
	
	@Override
	public boolean existsByPatientId(String patientId) {
		return consultationRepository.existsByPatientId(patientId);
	}
	
	@Override
	public boolean existsByDoctorId(String doctorId) {
		return consultationRepository.existsByDoctorId(doctorId);
	}
}
