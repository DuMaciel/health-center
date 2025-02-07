package br.edu.utfpr.td.tsi.health_center.model.dto;

import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.ConsultationStatus;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

@ModelAnnotation(model=Diagnosis.class)
public class DiagnosisDTO implements BaseDTO {
	private String id;
	private String details;
	private String patientId;
	private String patientName;
	private String doctorId;
	private String doctorName;
	private String status;

	public DiagnosisDTO() {
	}

	public DiagnosisDTO(Diagnosis diagnosis) {
		super();
		this.id = diagnosis.getId();
		this.details = diagnosis.getDetails();

		Consultation consultation = diagnosis.getConsultation();
		Patient patient = consultation.getPatient();
		Doctor doctor = consultation.getDoctor();

		this.patientId = patient.getId();
		this.patientName = patient.getName();
		this.doctorId = doctor.getId();
		this.doctorName = doctor.getName();
		this.status = consultation.getStatus().getDescription();
	}

	public Diagnosis toModel() {
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(this.id);
		diagnosis.setDetails(this.details);

		Doctor doctor = new Doctor();
		doctor.setId(this.doctorId);
		doctor.setName(this.doctorName);

		Patient patient = new Patient();
		patient.setId(this.patientId);
		patient.setName(this.patientName);

		Consultation consultation = new Consultation();
		consultation.setStatus(ConsultationStatus.valueOf(this.status));
		consultation.setPatient(patient);
		consultation.setDoctor(doctor);

		diagnosis.setConsultation(consultation);

		return diagnosis;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}