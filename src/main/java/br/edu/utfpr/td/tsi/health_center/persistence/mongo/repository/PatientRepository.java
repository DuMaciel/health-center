package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.PatientMongo;

public interface PatientRepository extends MongoRepository<PatientMongo, String>, FindAllByNameExtension<PatientMongo> {
    public boolean existsByCpf(String cpf);
	public boolean existsByAddressDistrictId(String districtId);
	public List<PatientMongo> findAllByIdNotIn(List<String> ids);
}
