package br.edu.utfpr.td.tsi.health_center.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.model.Address;

public interface AddressRepository extends MongoRepository<Address, String>   {

}
