package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.ConsultationStatus;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.service.ConsultationService;

@Service
public class ConsultationServiceImp implements ConsultationService{
	@Autowired
	private ConsultationAdapter consultationAdapter;
	
	@Override
	public void add(Consultation consultation) {
		String patientId = consultation.getPatient().getId();
		
		LocalDateTime dateTime = consultation.getDateTime();
		
		long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), dateTime);
		if(hours < 3) {
			throw new RuntimeException("A consulta deve ser marcada com pelo menos 3 horas de antecedência.");
		}
		
		long years = ChronoUnit.YEARS.between(LocalDateTime.now(), dateTime);
		if(years >= 1) {
			throw new RuntimeException("A consulta não deve ser agendada com mais de 1 ano de antecedência.");
		}
		
		if (consultationAdapter.existsByPatientIdAndStatus(patientId, ConsultationStatus.SCHEDULED)) {
			throw new RuntimeException("Este paciente já tem uma consulta agendada.");
		}
		
		consultation.setStatus(ConsultationStatus.SCHEDULED);
		
		consultationAdapter.save(consultation);
	}

	@Override
	public void edit(Consultation consultation) {
		String consultationId = consultation.getId();
		
		Consultation consultationSaved = consultationAdapter.find(consultationId);
		consultation.setStatus(consultationSaved.getStatus());
		
		consultationAdapter.save(consultation);
	}

	@Override
	public Consultation find(String id) {
		return consultationAdapter.find(id);
	}

	@Override
	public void delete(String id) {
		// TO DO APLICAR LOGICA PARA NAO DELETER CONSULTAS EM ANDAMENTO
		consultationAdapter.delete(id);
	}
	
	@Override
	public List<Consultation> findAll(String patientId, String DoctorId) {
		return consultationAdapter.findAll(patientId, DoctorId);
	}
	
	@Override
	public void completeConsultation(String consultationId) {
		Consultation consultationSaved = consultationAdapter.find(consultationId);
		consultationSaved.setStatus(ConsultationStatus.CANCELED);
		
		consultationAdapter.save(consultationSaved);
	}
	
	@Override
	public void cancelConsultation(String consultationId) {
		Consultation consultationSaved = consultationAdapter.find(consultationId);
		consultationSaved.setStatus(ConsultationStatus.CANCELED);
		
		consultationAdapter.save(consultationSaved);
	}

	// TODO IMPLEMENTAR A BUSCA POR TODOS
}
