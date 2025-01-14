package br.edu.utfpr.td.tsi.health_center.controller.dto;
import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

public class PatientDTO {
	private String id;
	@FilterName(name = "Nome")
	private String name;
	@FilterName(name = "CPF")
	private String cpf;
	@FilterName(name = "CEP")
	private String addressPostalCode;
	private String districtId;
	@FilterName(name = "Distrito")
	private String districtName;
	@FilterName(name = "Rua")
	private String addressStreet;
	@FilterName(name = "Numero")
	private Integer addressNumber;
	private String addressComplement;
	
	public PatientDTO() {}

	public PatientDTO(Patient patient) {
		this.id = patient.getId();
		this.name = patient.getName();
		this.cpf = patient.getCpf();
		
		Address address = patient.getAddress();
		District district = address.getDistrict();
		
		this.addressPostalCode = address.getPostalCode();
		this.districtId = district.getId();
		this.districtName = district.getName();
		this.addressStreet = address.getStreet();
		this.addressNumber = address.getNumber();
		this.addressComplement = address.getComplement();
	}

	public Patient toModel() {
		Patient patient = new Patient();
		patient.setId(this.id);
		patient.setName(this.name);
		patient.setCpf(this.cpf);

		Address address = new Address();
		address.setPostalCode(this.addressPostalCode);
		address.setStreet(this.addressStreet);
		address.setNumber(this.addressNumber);
		address.setComplement(this.addressComplement);

		District district = new District();
		district.setId(this.districtId);
		district.setName(this.districtName);
		
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

	public String getAddressPostalCode() {
		return addressPostalCode;
	}

	public void setAddressPostalCode(String addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public Integer getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(Integer addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}
}
