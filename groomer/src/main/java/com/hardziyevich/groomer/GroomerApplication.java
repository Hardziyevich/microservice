package com.hardziyevich.groomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hardziyevich.groomer","com.hardziyevich.resource"})
public class GroomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroomerApplication.class, args);
	}

}
