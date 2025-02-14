package br.edu.utfpr.td.tsi.health_center.persistence.mongo.adapter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.model.dto.DiagnosisDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.DiagnosisAdapter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.IndexerService;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.DiagnosisMapper;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DiagnosisMongo;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.repository.DiagnosisRepository;

@Component
@Profile("mongo")
public class DiagnosisMongoAdapter implements DiagnosisAdapter {
	
	@Autowired
	private DiagnosisRepository diagnosisRepository;
	
	@Autowired
	private ConsultationAdapter consultationAdapter;
	
	@Autowired
	private IndexerService indexerService;
	
	@Override
	public void save(Diagnosis diagnosis) {
		DiagnosisMongo diagnosisMongo = DiagnosisMapper.toMongo(diagnosis);
		diagnosisMongo = diagnosisRepository.save(diagnosisMongo);
		diagnosis.setId(diagnosisMongo.getId());
		indexerService.save(new DiagnosisDTO(diagnosis));
	}

	@Override
	public Diagnosis find(String id) {
		DiagnosisMongo diagnosisMongo = diagnosisRepository.findById(id).get();
		String consultationId = diagnosisMongo.getConsultationId();
		Consultation consultation = consultationAdapter.find(consultationId);
		return DiagnosisMapper.toDomain(diagnosisMongo, consultation);
	}
	
	private List<Consultation> findConsultations(List<DiagnosisMongo> diagnosticsMongo){
		List<String> consultationsIds = new ArrayList<String>();
		for (DiagnosisMongo diagnosisMongo : diagnosticsMongo) {
			consultationsIds.add(diagnosisMongo.getConsultationId());
		}
		return consultationAdapter.findAllByIds(consultationsIds);
	}

	@Override
	public List<Diagnosis> findAll() {
		List<DiagnosisMongo> diagnosticsMongo = diagnosisRepository.findAll();
		List<Consultation> consultations = findConsultations(diagnosticsMongo);
		return DiagnosisMapper.toDomainList(diagnosticsMongo, consultations);
	}
	
	@Override
	public List<Diagnosis> findAllByIds(List<String> ids){
		List<DiagnosisMongo> diagnosticsMongo = (List<DiagnosisMongo>) diagnosisRepository.findAllById(ids);
		List<Consultation> consultations = findConsultations(diagnosticsMongo);
		return DiagnosisMapper.toDomainList(diagnosticsMongo, consultations);
	}
	
	@Override
	public List<Diagnosis> findAllByIdsNotIn(List<String> ids) {
		List<DiagnosisMongo> diagnosticsMongo = (List<DiagnosisMongo>) diagnosisRepository.findAllByIdNotIn(ids);
		List<Consultation> consultations = findConsultations(diagnosticsMongo);
		return DiagnosisMapper.toDomainList(diagnosticsMongo, consultations);
	}
	
	@Override
	public List<Diagnosis> findAllByFilter(Filter filter) {
		List<String> diagnosticsIds = indexerService.searchIds(DiagnosisDTO.class, filter);
		return findAllByIds(diagnosticsIds);
	}

	@Override
	public void delete(String id) {
		diagnosisRepository.deleteById(id);
		indexerService.delete(DiagnosisDTO.class, id);
	}

}
