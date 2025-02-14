package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.service.DistrictService;

@Service
public class DistrictServiceImp implements DistrictService {
	@Autowired
	private DistrictAdapter districtAdapter;
	
	@Autowired
	private DoctorAdapter doctorAdapter;
	
	@Autowired
	private PatientAdapter patientAdapter;

	@Override
	public void add(District district) {
		if(districtAdapter.existsByName(district.getName())){
			throw new RuntimeException("Um bairro com esse nome já existe.");
		}
		districtAdapter.save(district);
	}

	@Override
	public void edit(District district) {
		if(districtAdapter.existsByName(district.getName())){
			District districtSaved = districtAdapter.find(district.getId());
			if(!districtSaved.getName().toLowerCase().equals(district.getName().toLowerCase())) {
				throw new RuntimeException("Um bairro com esse nome já existe.");
			}
		}
		districtAdapter.save(district);
	}

	@Override
	public District find(String id) {
		return districtAdapter.find(id);
	}

	@Override
	public void delete(String id) {
		if(patientAdapter.existsByAddressDistrictId(id)){
			throw new RuntimeException("Existe um paciente nesse distrito.");
		}
		if(doctorAdapter.existsByAddressDistrictId(id)){
			throw new RuntimeException("Existe um médico nesse distrito.");
		}
		districtAdapter.delete(id);
	}

	@Override
	public List<District> findAll(String name) {
		if(name != null && !name.equals("")) {
			return districtAdapter.findAll(name);
		}
		return districtAdapter.findAll();
	}

	@Override
	public List<District> findAllByFilter(Filter filter) {
		return districtAdapter.findAllByFilter(filter);
	}
}
