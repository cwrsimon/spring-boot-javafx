package de.wesim.springbootjavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootJavafxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJavafxApplication.class, args);
	}
}

