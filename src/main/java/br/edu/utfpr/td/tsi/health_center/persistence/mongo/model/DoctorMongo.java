package br.edu.utfpr.td.tsi.health_center.persistence.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("doctor")
public class DoctorMongo {
	@Id
	private String id;
	private String name;
	private String cpf;
	private String crm;
	private AddressMongo address;
	
	public DoctorMongo() {
		
	}
	
	public DoctorMongo(String id, String name, String cpf, String crm, AddressMongo address) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.crm = crm;
		this.address = address;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCrm() {
		return crm;
	}

	public AddressMongo getAddress() {
		return address;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public void setAddress(AddressMongo address) {
		this.address = address;
	}
}
