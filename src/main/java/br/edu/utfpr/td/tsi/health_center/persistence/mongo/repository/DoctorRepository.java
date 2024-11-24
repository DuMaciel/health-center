package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.persistence.FindAllByNameExtension;

public interface DoctorRepository extends MongoRepository<Doctor, String>, FindAllByNameExtension<Doctor> {
}