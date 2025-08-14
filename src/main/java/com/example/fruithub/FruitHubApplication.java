package com.example.fruithub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.fruithub")
public class FruitHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitHubApplication.class, args);
	}

}
