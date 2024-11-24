package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;

public interface ConsultationRepository extends MongoRepository<Consultation, String> {
}
