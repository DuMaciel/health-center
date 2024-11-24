package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.PatientMongo;

public interface PatientRepository extends MongoRepository<PatientMongo, String>, FindAllByNameExtension<PatientMongo> {
	@Query(value = "{ 'cpf': ?0 }", exists = true)
    public boolean existsByCpf(String cpf);
}
