package br.edu.utfpr.td.tsi.health_center.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.model.District;

public interface DistrictRepository extends MongoRepository<District, String>, FindAllByNameExtension<District> {
	@Query(value = "{ 'name': { $regex: '^?0$', $options: 'i' } }", exists = true)
    public boolean existsByName(String name);
}
