package br.edu.utfpr.td.tsi.health_center.controller.dto;

import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;

public class DoctorDTO {
	
	private String id;
	private String name;
	private String cpf;
	private String crm;
	private String street;
	private String cep;
	private String idDistrict;
	private String nameDistrict;
	private int addressNumber;
	
	public DoctorDTO(Doctor doctor) {
		this.id = doctor.getId();
		this.name = doctor.getName();
		this.cpf = doctor.getCpf();
		this.crm = doctor.getCrm();
		this.street = doctor.getAddress().getStreet();
		this.cep = doctor.getAddress().getPostalCode();
		this.idDistrict = doctor.getAddress().getDistrict().getId();
		this.nameDistrict = doctor.getAddress().getDistrict().getName();
		this.addressNumber = doctor.getAddress().getNumber();
	}
	
	public Doctor convertToModel() {
		Doctor doctor = new Doctor();
		doctor.setId(this.id);
		doctor.setName(this.name);
		doctor.setCpf(this.cpf);
		doctor.setCrm(this.crm);
		
		Address address = new Address();
		address.setStreet(this.street);
		address.setPostalCode(this.cep);
		address.setNumber(addressNumber);
		
		District district = new District();
		district.setId(idDistrict);
		district.setName(nameDistrict);
		
		address.setDistrict(district);
		doctor.setAddress(address);
		
		return doctor;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
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
