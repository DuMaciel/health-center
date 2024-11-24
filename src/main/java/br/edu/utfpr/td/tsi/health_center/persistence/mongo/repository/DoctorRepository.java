package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.persistence.FindAllByNameExtension;

public interface DoctorRepository extends MongoRepository<Doctor, String>, FindAllByNameExtension<Doctor> {
	@Query(value = "{ 'cpf': ?0 }", exists = true)
    public boolean existsByCpf(String cpf);
	
	@Query(value = "{ 'crm': ?0 }", exists = true)
    public boolean existsByCrm(String crm);
}