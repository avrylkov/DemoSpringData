package com.example.demoSpringData;

import com.example.demoSpringData.model.Employees;
import com.example.demoSpringData.repositories.CustomizedEmployeesCrudRepository;
import com.sun.xml.internal.ws.policy.AssertionValidationProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DemoSpringDataApplication.class })
public class DemoSpringDataApplicationTests {

	@Autowired
    private CustomizedEmployeesCrudRepository employeesCrudRepository;

	@Autowired
	private KeyValueOperations keyValueTemplate;

	@Before
	public void init() {
		Locale.setDefault(Locale.ENGLISH);
	}

	@Test
	@Commit
	public void testAddEmployeesCrudRepository() {
		Optional<Employees> optionalEmployees = employeesCrudRepository.findById(127L);
		Assert.assertTrue(optionalEmployees.isPresent());

		keyValueTemplate.insert(optionalEmployees.get());
        Optional<Employees> employeesOptional = keyValueTemplate.findById(127L, Employees.class);
        Assert.assertTrue(employeesOptional.isPresent());

		//Optional<Employees> james = employeesCrudRepository.findOne(QEmployees.employees.firstName.eq("James"));
		//Assert.assertTrue(james.isPresent());
	}

}

