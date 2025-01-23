package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.DistrictAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DoctorAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.PatientAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.DistrictIndexer;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.DoctorIndexer;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.PatientIndexer;

@Service
@Profile("solr")
public class SolrDataLoaderService {
	@Autowired
    private PatientIndexer patientIndexer;
	
	@Autowired
    private DoctorIndexer doctorIndexer;
	
	@Autowired
    private DistrictIndexer districtIndexer;
	
	@Autowired
	private PatientAdapter patientAdapter;
	
	@Autowired
	private DoctorAdapter doctorAdapter;
	
	@Autowired
	private DistrictAdapter districtAdapter;
	
	public void loadDataToSolr() throws Exception {
		List<String> patientsIds = patientIndexer.getIds();
		List<Patient> patients = patientAdapter.findAllByIdsNotIn(patientsIds);
		if(!patients.isEmpty()) {
			patientIndexer.save(patients);
		}
		
		List<String> doctorsIds = doctorIndexer.getIds();
		List<Doctor> doctors = doctorAdapter.findAllByIdsNotIn(doctorsIds);
		if(!doctors.isEmpty()) {
			doctorIndexer.save(doctors);
		}
		
		List<String> districtsIds = districtIndexer.getIds();
		List<District> districts = districtAdapter.findAllByIdsNotIn(districtsIds);
		if(!districts.isEmpty()) {
			districtIndexer.save(districts);
		}
	}
}
