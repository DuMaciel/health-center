package br.edu.utfpr.td.tsi.health_center.persistence.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PatientMongo {
	@Id
	private String id;
	private String name;
	private String cpf;
	private AddressMongo address;
	
	public PatientMongo() {
		
	}

	public PatientMongo(String id, String name, String cpf, AddressMongo address) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
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

	public void setAddress(AddressMongo address) {
		this.address = address;
	}
}
