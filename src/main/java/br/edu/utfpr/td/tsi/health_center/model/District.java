package br.edu.utfpr.td.tsi.health_center.model;

public class District implements BaseModel {
	private String id;
	private String name;
	
	public District() {
		
	}
	
	public District(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", name=" + name + "]";
	}
}
