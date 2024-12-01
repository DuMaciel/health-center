package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.model.ConsultationStatus;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.ConsultationMongo;

public interface ConsultationRepository extends MongoRepository<ConsultationMongo, String> {
	public List<ConsultationMongo> findAllByPatientId(String patientId);
	
	public List<ConsultationMongo> findAllByDoctorId(String doctorId);
	
	public List<ConsultationMongo> findAllByPatientIdAndDoctorId(String patientId, String doctorId);
	
	public List<ConsultationMongo> findAllByPatientIdAndStatus(String patientId, ConsultationStatus status);
	
	public boolean existsByPatientId(String patientId);
	
	public boolean existsByDoctorId(String doctorId);
	
	public boolean existsByPatientIdAndStatus(String patientId, ConsultationStatus status);
}
