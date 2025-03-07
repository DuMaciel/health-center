package br.edu.utfpr.td.tsi.health_center.service;

import br.edu.utfpr.td.tsi.health_center.model.Patient;

public interface PatientService extends BaseService<Patient>, FindAllExtension<Patient>, FindAllByFilter<Patient> {
}
