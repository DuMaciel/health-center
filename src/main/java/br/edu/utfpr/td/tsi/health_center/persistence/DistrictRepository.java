package br.edu.utfpr.td.tsi.health_center.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.model.District;

public interface DistrictRepository extends MongoRepository<District, String>, FindAllByNameExtension<District> {
}