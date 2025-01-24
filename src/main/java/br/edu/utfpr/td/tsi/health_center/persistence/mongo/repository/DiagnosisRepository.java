package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DiagnosisMongo;

public interface DiagnosisRepository extends MongoRepository<DiagnosisMongo, String>, FindAllByIdNotInExtension<DiagnosisMongo>{
	
}
