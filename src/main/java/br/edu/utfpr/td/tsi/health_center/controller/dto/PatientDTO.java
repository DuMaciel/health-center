package br.edu.utfpr.td.tsi.health_center.controller.dto;
import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

public class PatientDTO {
	
	private String id;
	private String name;
	private String cpf;
	private String street;
	private String cep;
	private String idDistrict;
	private String nameDistrict;
	private int addressNumber;
	private String complement;
	
	public PatientDTO(Patient patient) {
		this.id = patient.getId();
		this.name = patient.getName();
		this.cpf = patient.getCpf();
		this.street = patient.getAddress().getStreet();
		this.idDistrict = patient.getAddress().getDistrict().getId();
		this.nameDistrict = patient.getAddress().getDistrict().getName();
		this.cep = patient.getAddress().getPostalCode();
		this.addressNumber = patient.getAddress().getNumber();
		this.complement = patient.getAddress().getComplement();
	}

	public Patient convertToModel() {
		Patient patient = new Patient();
		patient.setId(this.id);
		patient.setName(this.name);
		patient.setCpf(this.cpf);

		Address address = new Address();
		address.setPostalCode(this.cep);
		address.setStreet(this.street);
		address.setNumber(this.addressNumber);
		address.setComplement(this.complement);

		District district = new District();
		district.setId(this.idDistrict);
		district.setName(this.nameDistrict);
		
		address.setDistrict(district);
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
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
	
}
