package br.edu.utfpr.td.tsi.health_center.persistence.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.persistence.mongo.model.ConsultationMongo;

public class ConsultationMapper {
	
	static public ConsultationMongo toMongo(Consultation consultation) {
		ConsultationMongo consultationMongo = new ConsultationMongo();
		consultationMongo.setId(consultation.getId());
		consultationMongo.setDateTime(consultation.getDateTime());
		consultationMongo.setStatus(consultation.getStatus());
		consultationMongo.setDoctorId(consultation.getDoctor().getId());
		consultationMongo.setPatientId(consultation.getPatient().getId());
		return consultationMongo;
	}
	
	static public Consultation toDomain(ConsultationMongo consultationMongo, Doctor doctor, Patient 
			patient) {
		Consultation consultation = new Consultation();
		consultation.setId(consultationMongo.getId());
		consultation.setDateTime(consultationMongo.getDateTime());
		consultation.setStatus(consultationMongo.getStatus());
		consultation.setDoctor(doctor);
		consultation.setPatient(patient);
		return consultation;
	}
	
	static public List<ConsultationMongo> toMongoList(List<Consultation> consultations) {
		List<ConsultationMongo> consultationsMongo = new ArrayList<ConsultationMongo>();
		
		for (Consultation consultation : consultations) {
			consultationsMongo.add(toMongo(consultation));
		}
		
		return consultationsMongo;
	}
	
	static public List<Consultation> toDomainList(List<ConsultationMongo> consultationsMongo, List<Doctor> doctors, List<Patient> patients) {
		List<Consultation> consultations = new ArrayList<Consultation>();
		Map<String, Doctor> doctorMap = new HashMap<String, Doctor>();
		Map<String, Patient> patientMap = new HashMap<String, Patient>();
		
		for (Doctor doctor : doctors) {
			doctorMap.put(doctor.getId(), doctor);
		}
		
		for (Patient patient : patients) {
			patientMap.put(patient.getId(), patient);
		}
		
		for (ConsultationMongo consultationMongo : consultationsMongo) {
			Doctor doctor = doctorMap.get(consultationMongo.getDoctorId());
			Patient patient = patientMap.get(consultationMongo.getPatientId());
			consultations.add(toDomain(consultationMongo, doctor, patient));
		}
		
		return consultations;
	}
}
