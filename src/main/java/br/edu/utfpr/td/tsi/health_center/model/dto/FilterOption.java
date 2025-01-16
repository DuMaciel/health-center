package br.edu.utfpr.td.tsi.health_center.model.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FilterOption {

	private String name;
	private String value;

	public FilterOption(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	static public List<FilterOption> getFilterOptions(Class<?> c) {
		List<FilterOption> filterOptions = new ArrayList<>();
		for (Field field : c.getDeclaredFields()) {
			if (field.isAnnotationPresent(FilterName.class)) {
				FilterName annotation = field.getAnnotation(FilterName.class);
				field.setAccessible(true);
				filterOptions.add(new FilterOption(annotation.name(), field.getName()));
			}
		}
		return filterOptions;
	}
	
}
