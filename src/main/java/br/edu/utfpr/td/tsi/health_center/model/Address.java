package br.edu.utfpr.td.tsi.health_center.model;

public class Address {
	private String id;
	private String postalCode;
	private District district;
	private String street;
	private Integer number;
	private String complement;
	
	public Address() {
		
	}
	
	public Address(String id, String postalCode, District district, String street, Integer number, String complement) {
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

	public Integer getNumber() {
		return number;
	}

	public String getComplement() {
		return complement;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", postalCode=" + postalCode + ", district=" + district + ", street=" + street
				+ ", number=" + number + ", complement=" + complement + "]";
	}
}
