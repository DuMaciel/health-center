package br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DoctorMongo;

public interface DoctorRepository extends MongoRepository<DoctorMongo, String>, FindAllByNameExtension<DoctorMongo> {
    public boolean existsByCpf(String cpf);
    public boolean existsByCrm(String crm);
    public boolean existsByAddressDistrictId(String districtId);
}