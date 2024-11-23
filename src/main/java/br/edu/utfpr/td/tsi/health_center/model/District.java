package br.edu.utfpr.td.tsi.health_center.model;

public class District {
	private int id;
	private String name;
	
	public District(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
