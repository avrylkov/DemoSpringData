package com.example.demoSpringData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
@ImportResource({"classpath:context.xml"})
public class DemoSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}

}

