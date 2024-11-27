package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.persistence.FindAllByNameExtension;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DoctorMongo;

public interface DoctorRepository extends MongoRepository<DoctorMongo, String>, FindAllByNameExtension<DoctorMongo> {
	@Query(value = "{ 'cpf': ?0 }", exists = true)
    public boolean existsByCpf(String cpf);
	
	@Query(value = "{ 'crm': ?0 }", exists = true)
    public boolean existsByCrm(String crm);
}