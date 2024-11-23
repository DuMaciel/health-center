package br.edu.utfpr.td.tsi.health_center.model;

public class Address {
	private int id;
	private String postalCode;
	private District district;
	private String street;
	private int number;
	private String complement;
	
	public Address(int id, String postalCode, District district, String street, int number, String complement) {
		super();
		this.id = id;
		this.postalCode = postalCode;
		this.district = district;
		this.street = street;
		this.number = number;
		this.complement = complement;
	}

	public int getId() {
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
