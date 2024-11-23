package br.edu.utfpr.td.tsi.health_center.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.health_center.model.Address;

public interface AddressRepository extends MongoRepository<Address, String> {
	@Query(value = "{ 'districtId': ?0 }", exists = true)
    public boolean existsByDistrictId(String name);
}
