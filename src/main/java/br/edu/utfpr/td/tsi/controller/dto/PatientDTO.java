package br.edu.utfpr.td.tsi.controller.dto;
import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

public class PatientDTO {
	
	private String id;
	private String name;
	private String street;
	private String cep;
	private String idDistrict;
	private String nameDistrict;
	private int addressNumber;
	
	public PatientDTO(String id, String name, String street, String cep, String idDistrict, String nameDistrict,
			int addressNumber) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.cep = cep;
		this.idDistrict = idDistrict;
		this.nameDistrict = nameDistrict;
		this.addressNumber = addressNumber;
	}
	
	public PatientDTO(Patient patient) {
		this.id = patient.getId();
		this.name = patient.getName();
		this.street = patient.getAddress().getStreet();
		this.nameDistrict = patient.getAddress().getDistrict().getName();
		this.cep = patient.getAddress().getPostalCode();
	}

	public Patient converterParaModelo() {
		Patient patient = new Patient();
		patient.setId(this.id);
		patient.setName(this.name);

		Address address = new Address();
		address.setPostalCode(this.cep);
		address.setStreet(this.street);
		address.setNumber(this.addressNumber);

		District district = new District();
		district.setId(this.idDistrict);
		district.setName(this.nameDistrict);

		patient.setAddress(address);

		return patient;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getIdDistrict() {
		return idDistrict;
	}

	public void setIdDistrict(String idDistrict) {
		this.idDistrict = idDistrict;
	}

	public String getNameDistrict() {
		return nameDistrict;
	}

	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}

	public int getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}
	
	
}
