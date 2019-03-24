package com.example.demoSpringData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.keyvalue.core.KeyValueAdapter;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.map.MapKeyValueAdapter;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@ImportResource({"classpath:context.xml"})
public class DemoSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}

	@Bean
	public KeyValueOperations keyValueTemplate() {
		return new KeyValueTemplate(keyValueAdapter());
	}

	@Bean
	public KeyValueAdapter keyValueAdapter() {
		return new MapKeyValueAdapter(ConcurrentHashMap.class);
	}

}

