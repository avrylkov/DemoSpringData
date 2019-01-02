package com.example.demoSpringData;

import com.example.demoSpringData.model.Employees;
import com.example.demoSpringData.repositories.EmployeesCrudRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringDataApplicationTests {

	@Autowired
    private EmployeesCrudRepository employeesCrudRepository;


	@Before
	public void init() {
		Locale.setDefault(Locale.ENGLISH);
	}

	@Test
	public void testEmployeesCrudRepository() {
		Optional<Employees> employeesOptional = employeesCrudRepository.findById(127L);
		System.out.println(employeesOptional
				.map(e -> e.getFirstName() + " " + e.getLastName())
				.orElse("not found"));
	}

}

