package br.edu.utfpr.td.tsi.health_center.model.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)


@Retention(RetentionPolicy.RUNTIME)
public @interface FilterAnnotation {
	String name();
	Class<? extends BaseDTO> relatedEntity() default BaseDTO.class;
    String relatedField() default "";
}
