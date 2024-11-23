package br.edu.utfpr.td.tsi.health_center.model.service;

import br.edu.utfpr.td.tsi.health_center.model.Patient;

public interface PatientService {
	public void add(Patient patient);
	
	public void edit(Patient patient);
	
	public Patient find(String id);
	
	public Patient findAll(String... name);
	
	public void delete(String id);
}
