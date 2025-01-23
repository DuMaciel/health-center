package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DistrictMongo;

public interface DistrictRepository extends MongoRepository<DistrictMongo, String>, FindAllByIdNotInExtension<DistrictMongo>, FindAllByNameExtension<DistrictMongo> {
	@Query(value = "{ 'name': { $regex: '^?0$', $options: 'i' } }", exists = true)
    public boolean existsByName(String name);
}
