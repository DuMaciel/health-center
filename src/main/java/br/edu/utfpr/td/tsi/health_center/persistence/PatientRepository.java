package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.model.Patient;

public interface PatientRepository extends MongoRepository<Patient, String>   {
	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	List<Patient> findAllByName(String search);
}
