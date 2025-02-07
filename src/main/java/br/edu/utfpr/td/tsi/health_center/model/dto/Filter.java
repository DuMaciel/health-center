package br.edu.utfpr.td.tsi.health_center.model.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
			if (field.isAnnotationPresent(FilterAnnotation.class)) {
				if (this.field.equals(field.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	static public FilterAnnotation getFilterAnnotation(Class<?> c, String fieldName) {
		for (Field field : c.getDeclaredFields()) {
			if (fieldName.equals(field.getName())) {
				return field.getAnnotation(FilterAnnotation.class);
			}
		}
		return null;
	}

	static public Set<String> getFieldsName(Class<?> c) {
		Set<String> fieldsName = new HashSet<String>();
		for (Field field : c.getDeclaredFields()) {
			if (field.isAnnotationPresent(FilterAnnotation.class)) {
				String name = field.getName();
				fieldsName.add(name);
			}
		}
		return fieldsName;
	}

	static public Map<String, Object> getFields(Class<?> c, Object o) {
		Map<String, Object> fields = new HashMap<String, Object>();
		for (Field field : c.getDeclaredFields()) {
			if (field.isAnnotationPresent(FilterAnnotation.class)) {
				try {
					String name = field.getName();
					field.setAccessible(true);
					Object value = field.get(o);
					fields.put(name, value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return fields;
	}
}
