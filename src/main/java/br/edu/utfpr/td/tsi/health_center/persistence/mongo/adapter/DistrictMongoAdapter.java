package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.DistrictMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DistrictMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DistrictRepository;

@Component
public class DistrictMongoAdapter implements DistrictAdapter {
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public void save(District district) {
		DistrictMongo districtMongo = DistrictMapper.toMongo(district);
		districtRepository.save(districtMongo);
	}

	@Override
	public District find(String id) {
		DistrictMongo districtMongo = districtRepository.findById(id).get();
		return DistrictMapper.toDomain(districtMongo);
	}

	@Override
	public List<District> findAll() {
		List<DistrictMongo> districtMongo = districtRepository.findAll();
		return DistrictMapper.toDomainList(districtMongo);
	}

	@Override
	public void delete(String id) {
		districtRepository.deleteById(id);
	}

	@Override
	public boolean existsByName(String name) {
		return districtRepository.existsByName(name);
	}
	
	@Override
	public List<District> findAllByName(String name) {
		List<DistrictMongo> districtMongo = districtRepository.findAllByName(name);
		return DistrictMapper.toDomainList(districtMongo);
	}
}
