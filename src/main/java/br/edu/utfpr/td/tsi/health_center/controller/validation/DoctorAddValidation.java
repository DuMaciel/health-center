package br.edu.utfpr.td.tsi.health_center.controller.validation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorAddValidation {

	@NotEmpty(message = "Nome é obrigatório.")
	String name;
	@NotEmpty(message = "CPF é obrigatório.")
	String cpf;
	@NotEmpty(message = "CRM é obrigatório.")
	String crm;
	@NotEmpty(message = "Endereço é obrigatório.")
	String addressStreet;
	@NotEmpty(message = "CEP é obrigatório.")
	String addressPostalCode;
	@NotEmpty(message = "Selecione um bairro.")
	String districtId;
	String districtName;
	@NotNull(message = "Numero é obrigatório.")
	Integer addressNumber;
	String addressComplement;

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
