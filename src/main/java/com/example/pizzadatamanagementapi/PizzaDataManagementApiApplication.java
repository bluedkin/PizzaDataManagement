package com.example.pizzadatamanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PizzaDataManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaDataManagementApiApplication.class, args);
	}

}
