package br.edu.utfpr.td.tsi.health_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({ "file:./application.properties" })
public class Main {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/");
		SpringApplication.run(Main.class, args);
	}
}
