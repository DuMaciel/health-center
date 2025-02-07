package br.edu.utfpr.td.tsi.health_center.model.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)


@Retention(RetentionPolicy.RUNTIME)
public @interface ModelAnnotation {
	Class<?> model() default Void.class;
}