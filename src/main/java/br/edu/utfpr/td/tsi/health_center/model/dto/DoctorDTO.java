package br.edu.utfpr.td.tsi.health_center.model.dto;

import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;

public class DoctorDTO {
	
	private String id;
	private String name;
	private String cpf;
	private String crm;
	private String addressStreet;
	private String addressPostalCode;
	private String districtId;
	private String districtName;
	private Integer addressNumber;
	private String addressComplement;
	
	public DoctorDTO() {}
	
	public DoctorDTO(Doctor doctor) {
		this.id = doctor.getId();
		this.name = doctor.getName();
		this.cpf = doctor.getCpf();
		this.crm = doctor.getCrm();
		
		Address address = doctor.getAddress();
		District district = address.getDistrict();
		
		this.addressStreet = address.getStreet();
		this.addressPostalCode = address.getPostalCode();
		this.districtId = district.getId();
		this.districtName = district.getName();
		this.addressNumber = address.getNumber();
		this.addressComplement = address.getComplement();
	}
	
	public Doctor toModel() {
		Doctor doctor = new Doctor();
		doctor.setId(this.id);
		doctor.setName(this.name);
		doctor.setCpf(this.cpf);
		doctor.setCrm(this.crm);
		
		Address address = new Address();
		address.setStreet(this.addressStreet);
		address.setPostalCode(this.addressPostalCode);
		address.setNumber(this.addressNumber);
		address.setComplement(this.addressComplement);
		
		District district = new District();
		district.setId(this.districtId);
		district.setName(this.districtName);
		
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

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
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
