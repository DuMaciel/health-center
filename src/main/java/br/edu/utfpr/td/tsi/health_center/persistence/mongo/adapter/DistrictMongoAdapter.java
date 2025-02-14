package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.dto.DistrictDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.IndexerService;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.DistrictMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DistrictMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DistrictRepository;

@Component
@Profile("mongo")
public class DistrictMongoAdapter implements DistrictAdapter {
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private IndexerService indexerService;

	@Override
	public void save(District district) {
		DistrictMongo districtMongo = DistrictMapper.toMongo(district);
		districtMongo = districtRepository.save(districtMongo);
		district.setId(districtMongo.getId());
		indexerService.save(new DistrictDTO(district));
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
	public List<District> findAllByIds(List<String> ids){
		List<DistrictMongo> districtMongo = (List<DistrictMongo>) districtRepository.findAllById(ids);
		return DistrictMapper.toDomainList(districtMongo);
	}
	
	@Override
	public List<District> findAllByIdsNotIn(List<String> ids) {
		List<DistrictMongo> districtsMongo = (List<DistrictMongo>) districtRepository.findAllByIdNotIn(ids);
		return DistrictMapper.toDomainList(districtsMongo);
	}
	
	@Override
	public List<District> findAll(String name) {
		List<DistrictMongo> districtsMongo = districtRepository.findAllByName(name);
		return DistrictMapper.toDomainList(districtsMongo);
	}
	
	@Override
	public List<District> findAllByFilter(Filter filter) {
		List<String> districtsIds = indexerService.searchIds(DistrictDTO.class, filter);
		return findAllByIds(districtsIds);
	}

	@Override
	public void delete(String id) {
		districtRepository.deleteById(id);
		indexerService.delete(DistrictDTO.class, id);
	}

	@Override
	public boolean existsByName(String name) {
		return districtRepository.existsByName(name);
	}
}
