package br.edu.utfpr.td.tsi.health_center.model.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Filter {
	private String field;
	private String term;
	
	public Filter(String field, String term) {
		super();
		this.field = field;
		this.term = term;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public boolean isValidField(Class<?> c) {
		for (Field field : c.getDeclaredFields()) {
			if (field.isAnnotationPresent(FilterName.class)) {
				if(this.field.equals(field.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	static public Map<String, String> getFields(Class<?> c, Object o){
		Map<String, String> fields = new HashMap<String, String>();
		for (Field field : c.getDeclaredFields()) {
			if (field.isAnnotationPresent(FilterName.class)) {
				try {
					String name = field.getName();
					field.setAccessible(true);
					Object value = field.get(o);
					fields.put(name, value != null ? value.toString() : "");
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
			}
		}
		return fields;
	}
}
