package br.edu.utfpr.td.tsi.health_center.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String>, FindAllByNameExtension<Doctor> {
}