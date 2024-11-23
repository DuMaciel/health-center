package br.edu.utfpr.td.tsi.health_center.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.persistence.AddressRepository;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictRepository;

@Service
public class DistrictServiceImp implements DistrictService {
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void add(District district) {
		if(districtRepository.existsByName(district.getName())){
			throw new RuntimeException("Um bairro com esse nome já existe");
		}
		districtRepository.save(district);
	}

	@Override
	public void edit(District district) {
		if(districtRepository.existsByName(district.getName())){
			District districtSaved = districtRepository.findById(district.getId()).get();
			if(!districtSaved.getName().toLowerCase().equals(district.getName().toLowerCase())) {
				throw new RuntimeException("Um bairro com esse nome já existe");
			}
		}
		districtRepository.save(district);
	}

	@Override
	public District find(String id) {
		return districtRepository.findById(id).get();
	}

	@Override
	public void delete(String id) {
		if(addressRepository.existsByDistrictId(id)) {
			throw new RuntimeException("Esse bairro está sendo utilizado em um endereço");
		}
		districtRepository.deleteById(id);
	}

	@Override
	public List<District> findAll(String name) {
		if(name != null && !name.equals("")) {
			return districtRepository.findAllByName(name);
		}
		return districtRepository.findAll();
	}
}
