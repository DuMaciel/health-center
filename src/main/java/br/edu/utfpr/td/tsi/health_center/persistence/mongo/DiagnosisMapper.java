package br.edu.utfpr.td.tsi.health_center.persistence.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.DiagnosisMongo;

public class DiagnosisMapper {
	static public DiagnosisMongo toMongo(Diagnosis diagnosis) {
		DiagnosisMongo diagnosisMongo = new DiagnosisMongo();
		diagnosisMongo.setId(diagnosis.getId());
		diagnosisMongo.setDetails(diagnosis.getDetails());
		diagnosisMongo.setConsultationId(diagnosis.getConsultation().getId());
		return diagnosisMongo;
	}
	
	static public Diagnosis toDomain(DiagnosisMongo diagnosisMongo, Consultation consultation) {
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(diagnosisMongo.getId());
		diagnosis.setDetails(diagnosisMongo.getDetails());
		diagnosis.setConsultation(consultation);
		return diagnosis;
	}
	
	static public List<DiagnosisMongo> toMongoList(List<Diagnosis> diagnostics) {
		List<DiagnosisMongo> diagnosticsMongo = new ArrayList<DiagnosisMongo>();
		for (Diagnosis diagnosis : diagnostics) {
			diagnosticsMongo.add(toMongo(diagnosis));
		}
		return diagnosticsMongo;
	}
	
	static public List<Diagnosis> toDomainList(List<DiagnosisMongo> diagnosticsMongo, List<Consultation> consultations) {
		List<Diagnosis> diagnostics = new ArrayList<Diagnosis>();
		Map<String, Consultation> consultationMap = new HashMap<String, Consultation>();
		
		for (Consultation consultation : consultations) {
			consultationMap.put(consultation.getId(), consultation);
		}
		
		for (DiagnosisMongo diagnosisMongo : diagnosticsMongo) {
			Consultation consultation = consultationMap.get(diagnosisMongo.getConsultationId());
			diagnostics.add(toDomain(diagnosisMongo, consultation));
		}
		return diagnostics;
	}
}
