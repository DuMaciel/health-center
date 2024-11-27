package br.edu.utfpr.td.tsi.health_center.persistence.mongo;

import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.AddressMongo;

public class AddressMapper {
	static public AddressMongo toMongo(Address address) {
		AddressMongo addressMongo = new AddressMongo();
		addressMongo.setId(address.getId());
		addressMongo.setPostalCode(address.getPostalCode());
		addressMongo.setStreet(address.getStreet());
		addressMongo.setNumber(address.getNumber());
		addressMongo.setComplement(address.getComplement());
		addressMongo.setDistrictId(address.getDistrict().getId());
		return addressMongo;
	}
	
	static public Address toDomain(AddressMongo addressMongo, District district) {
		Address address = new Address();
		address.setId(addressMongo.getId());
		address.setPostalCode(addressMongo.getPostalCode());
		address.setStreet(addressMongo.getStreet());
		address.setNumber(addressMongo.getNumber());
		address.setComplement(addressMongo.getComplement());
		address.setDistrict(district);
		return address;
	}
}
