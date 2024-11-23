package br.edu.utfpr.td.tsi.health_center.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
	@Id
	private String id;
	private String postalCode;
	private District district;
	private String street;
	private int number;
	private String complement;
	
	public Address(String id, String postalCode, District district, String street, int number, String complement) {
		super();
		this.id = id;
		this.postalCode = postalCode;
		this.district = district;
		this.street = street;
		this.number = number;
		this.complement = complement;
	}

	public String getId() {
		return id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public District getDistrict() {
		return district;
	}

	public String getStreet() {
		return street;
	}

	public int getNumber() {
		return number;
	}

	public String getComplement() {
		return complement;
	}
}
