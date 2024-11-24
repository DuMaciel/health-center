package br.edu.utfpr.td.tsi.health_center.persistence.mongo.model;

public class AddressMongo {
	private String id;
	private String postalCode;
	private String districtId;
	private String street;
	private Integer number;
	private String complement;

	public AddressMongo() {
	}

	public AddressMongo(String id, String postalCode, String districtId, String street, Integer number,
			String complement) {
		this.id = id;
		this.postalCode = postalCode;
		this.districtId = districtId;
		this.street = street;
		this.number = number;
		this.complement = complement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
}