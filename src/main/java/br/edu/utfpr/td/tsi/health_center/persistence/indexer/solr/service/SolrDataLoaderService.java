package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.dto.DistrictDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.DoctorDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.PatientDTO;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.IndexerService;

@Service
@Profile("solr")
public class SolrDataLoaderService {
	@Autowired
    private IndexerService indexerService;
	
	@Autowired
	private PatientAdapter patientAdapter;
	
	@Autowired
	private DoctorAdapter doctorAdapter;
	
	@Autowired
	private DistrictAdapter districtAdapter;
	
	public void loadDataToSolr() throws Exception {
		List<String> patientsIds = indexerService.getIds(PatientDTO.class);
		List<Patient> patients = patientAdapter.findAllByIdsNotIn(patientsIds);
		List<PatientDTO> patientsDTO = new ArrayList<PatientDTO>();
		for (Patient patient : patients) {
			patientsDTO.add(new PatientDTO(patient));
		}
		if(!patientsDTO.isEmpty()) {
			indexerService.save(patientsDTO);
		}
		
		List<String> doctorsIds = indexerService.getIds(DoctorDTO.class);
		List<Doctor> doctors = doctorAdapter.findAllByIdsNotIn(doctorsIds);
		List<DoctorDTO> doctorsDTO = new ArrayList<DoctorDTO>();
		for (Doctor doctor : doctors) {
			doctorsDTO.add(new DoctorDTO(doctor));
		}
		if(!doctorsDTO.isEmpty()) {
			indexerService.save(doctorsDTO);
		}
		
		List<String> districtsIds = indexerService.getIds(DistrictDTO.class);
		List<District> districts = districtAdapter.findAllByIdsNotIn(districtsIds);
		List<DistrictDTO> districtsDTO = new ArrayList<DistrictDTO>();
		for (District district : districts) {
			districtsDTO.add(new DistrictDTO(district));
		}
		if(!districtsDTO.isEmpty()) {
			indexerService.save(districtsDTO);
		}
	}
}
