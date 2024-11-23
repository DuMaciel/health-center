package br.edu.utfpr.td.tsi.health_center.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Patient {
	@Id
	private String id;
	private String name;
	private String cpf;
	private Address address;
	
	public Patient() {
		
	}

	public Patient(String id, String name, String cpf, Address address) {
		super();
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
	public Address getAddress() {
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

	public void setAddress(Address address) {
		this.address = address;
	}
}
