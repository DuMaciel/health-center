package br.edu.utfpr.td.tsi.health_center.model;

public class Patient {
	private int id;
	private String name;
	private String cpf;
	private Address address;
	
	public Patient(int id, String name, String cpf, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.address = address;
	}
	
	public int getId() {
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
}
