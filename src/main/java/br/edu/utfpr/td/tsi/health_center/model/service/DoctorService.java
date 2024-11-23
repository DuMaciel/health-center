package br.edu.utfpr.td.tsi.health_center.model.service;

import br.edu.utfpr.td.tsi.health_center.model.Doctor;

public interface DoctorService {
	public void add(Doctor doctor);
	
	public void edit(Doctor doctor);
	
	public Doctor find(String id);
	
	public Doctor findAll(String... name);
	
	public void delete(String id);
}
