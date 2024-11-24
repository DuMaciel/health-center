package br.edu.utfpr.td.tsi.health_center.persistence.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("district")
public class DistrictMongo {
	@Id
	private String id;
	private String name;
	
	public DistrictMongo() {
		
	}
	
	public DistrictMongo(String id, String name) {
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
}
