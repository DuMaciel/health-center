package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.ConsultationMongo;

public interface ConsultationRepository extends MongoRepository<ConsultationMongo, String> {
	@Query("{ $and: [ " +
            "{ $or: [ { ?0: null }, { 'pacienteId': ?0 } ] }, " +
            "{ $or: [ { ?1: null }, { 'doutorId': ?1 } ] } " +
           "] }")
	public List<ConsultationMongo> findAllByPatientIdAndDoctorId(String patientId, String doctorId);
}
