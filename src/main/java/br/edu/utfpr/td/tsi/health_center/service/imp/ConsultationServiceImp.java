package br.edu.utfpr.td.tsi.health_center.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.persistence.ConsultationAdapter;
import br.edu.utfpr.td.tsi.health_center.service.ConsultationService;

@Service
public class ConsultationServiceImp implements ConsultationService{

	@Autowired
	private ConsultationAdapter consultationAdapter;
	
	@Override
	public void add(Consultation consultation) {
		// TO DO LOGICA PARA NAO SALVAR CASO O PACIENTE TENHA CONSULTA
		// EM ANDAMENTO
		consultationAdapter.save(consultation);
	}

	@Override
	public void edit(Consultation consultation) {
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
	public List<Consultation> findAll() {
		return consultationAdapter.findAll();
	}

	// TODO IMPLEMENTAR A BUSCA POR TODOS
}
