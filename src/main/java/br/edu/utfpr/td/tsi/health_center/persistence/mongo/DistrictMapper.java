package br.edu.utfpr.td.tsi.health_center.persistence.mongo;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DistrictMongo;

public class DistrictMapper {
	static public DistrictMongo toMongo(District district) {
		DistrictMongo districtMongo = new DistrictMongo();
		districtMongo.setId(district.getId());
		districtMongo.setName(district.getName());
		return districtMongo;
	}
	
	static public District toDomain(DistrictMongo districtMongo) {
		District district = new District();
		district.setId(districtMongo.getId());
		district.setName(districtMongo.getName());
		return district;
	}
	
	static public List<DistrictMongo> toMongoList(List<District> districts) {
		List<DistrictMongo> districtsMongo = new ArrayList<DistrictMongo>();
		for (District district : districts) {
			districtsMongo.add(toMongo(district));
		}
		return districtsMongo;
	}
	
	static public List<District> toDomainList(List<DistrictMongo> districtsMongo) {
		List<District> districts = new ArrayList<District>();
		for (DistrictMongo districtMongo : districtsMongo) {
			districts.add(toDomain(districtMongo));
		}
		return districts;
	}
}
