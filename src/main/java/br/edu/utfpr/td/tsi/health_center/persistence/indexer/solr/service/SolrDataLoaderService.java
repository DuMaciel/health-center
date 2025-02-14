package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.BaseModel;
import br.edu.utfpr.td.tsi.health_center.model.dto.BaseDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.ConsultationDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.DistrictDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.DoctorDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.PatientDTO;
import br.edu.utfpr.td.tsi.health_center.persistence.BaseAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
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
	
	@Autowired
	private ConsultationAdapter consultationAdapter;
	
	public void loadDataToSolr() throws Exception {
		load(PatientDTO.class, patientAdapter);
		load(DoctorDTO.class, doctorAdapter);
		load(DistrictDTO.class, districtAdapter);
		load(ConsultationDTO.class, consultationAdapter);
	}
	
	private void load(Class<? extends BaseDTO> c, BaseAdapter<? extends BaseModel> adapter) {
		List<String> entityIds = indexerService.getIds(c);
		List<? extends BaseModel> entities = adapter.findAllByIdsNotIn(entityIds);
		List<BaseDTO> entitiesDTO = new ArrayList<>();
		for (BaseModel entity : entities) {
			try {
	            Constructor<? extends BaseDTO> constructor = c.getDeclaredConstructor(entity.getClass());
	            BaseDTO dtoInstance = constructor.newInstance(entity);
	            entitiesDTO.add(dtoInstance);
	        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
	            throw new RuntimeException("Erro ao instanciar DTO: " + c.getSimpleName(), e);
	        }
		}
		if(!entitiesDTO.isEmpty()) {
			indexerService.save(entitiesDTO);
		}
	}
}
