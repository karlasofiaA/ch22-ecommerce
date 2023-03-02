package org.generation.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ch22EccomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ch22EccomerceApplication.class, args);
	}
	
	@Bean //Para hacer un método inyectable
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
