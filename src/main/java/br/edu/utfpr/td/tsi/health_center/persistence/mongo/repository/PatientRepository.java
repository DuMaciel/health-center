package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.PatientMongo;

public interface PatientRepository extends MongoRepository<PatientMongo, String>, FindAllByIdNotInExtension<PatientMongo>, FindAllByNameExtension<PatientMongo> {
    public boolean existsByCpf(String cpf);
	public boolean existsByAddressDistrictId(String districtId);
}
