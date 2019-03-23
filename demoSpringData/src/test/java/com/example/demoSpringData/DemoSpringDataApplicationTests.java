package com.example.demoSpringData;

import com.example.demoSpringData.maps.Employees;
import com.example.demoSpringData.maps.QEmployees;
import com.example.demoSpringData.repositories.CustomizedEmployeesCrudRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Before
	public void init() {
		Locale.setDefault(Locale.ENGLISH);
	}

	@Test
	//@Transactional
	public void testEmployeesCrudRepository() {
		Optional<Employees> employeesOptional = employeesCrudRepository.findById(127L);

		Assert.assertTrue(employeesOptional.isPresent());

		System.out.println(employeesOptional
				.map(e -> e.getFirstName() + " " + e.getLastName())
				.orElse("not found"));

		//Employees landry = employeesCrudRepository.findFirstByLastName("Landry");
		//Assert.assertNotNull(landry);

		employeesOptional = employeesCrudRepository.findById(127L);
		Assert.assertTrue(employeesOptional.isPresent());

	}

	@Test
	@Commit
	public void testAddEmployeesCrudRepository() {
		Employees employees = new Employees();
		employees.setEmployeeId(127L);
		employees.setEmail("JLANDRY");
		employees.setFirstName("James");
		employees.setLastName("Landry");
		employees.setJobId("IT_PROG");
		employees.setHireDate(new Date());
		employeesCrudRepository.save(employees);

        Optional<Employees> employeesOptional = employeesCrudRepository.findById(127L);
        Assert.assertTrue(employeesOptional.isPresent());

		Optional<Employees> james = employeesCrudRepository.findOne(QEmployees.employees.firstName.eq("James"));
		Assert.assertTrue(james.isPresent());
	}

}

