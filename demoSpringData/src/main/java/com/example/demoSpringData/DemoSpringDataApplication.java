package com.example.demoSpringData;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@ImportResource({"classpath:context.xml"})
public class DemoSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}

//	@Bean
//	public SessionFactory sessionFactory(EntityManagerFactory emf) {
//		return emf.unwrap(SessionFactory.class);
//	}
}

