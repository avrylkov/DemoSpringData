package com.example.demoSpringData;

import com.example.demoSpringData.model.Employees;
import com.example.demoSpringData.repositories.CustomizedEmployeesCrudRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringDataApplicationTests {

	@Autowired
    private CustomizedEmployeesCrudRepository employeesCrudRepository;

	@Before
	public void init() {
		Locale.setDefault(Locale.ENGLISH);
	}

	@Test
	@Transactional
	public void testEmployeesCrudRepository() {
		Optional<Employees> employeesOptional = employeesCrudRepository.findById(127L);
		System.out.println(employeesOptional
				.map(e -> e.getFirstName() + " " + e.getLastName())
				.orElse("not found"));
	}

	@Test
	@Transactional
	@Commit
	public void testAddEmployeesCrudRepository() {
		Employees employees = new Employees();
		employees.setEmail("em@mail.ru");
		employees.setFirstName("Alex");
		employees.setLastName("Ivanov");
		employees.setJobId("IT_PROG");
		employees.setHireDate(Time.valueOf(LocalTime.now()));
		employeesCrudRepository.save(employees);
	}

	@Test
	@Transactional
	public void testFindByFirstNameAndAndLastName() {
		Optional<Employees> employeesOptional = employeesCrudRepository.findByFirstNameAndAndLastName("Alex", "Ivanov");
		System.out.println(employeesOptional
				.map(e -> e.getFirstName() + " " + e.getLastName())
				.orElse("not found"));
	}

	@Test
	@Transactional
	public void testFindFirst5ByFirstNameStartsWithOrderByFirstName() {
		List<Employees> list = employeesCrudRepository.findFirst5ByFirstNameStartsWithOrderByFirstName("D");
		list.forEach(e -> System.out.println(e.getFirstName() + " " +e.getLastName()));
	}

	@Test
	@Transactional
	@Commit
	public void testDeleteEmployees() {
		Optional<Employees> employeesOptional = employeesCrudRepository.findByFirstNameAndAndLastName("Alex", "Ivanov");
		employeesOptional.ifPresent(employeesCrudRepository::delete);
	}

	@Test
	@Transactional
	@Commit
	public void testMaxSalaryEmployees() {
		List<Employees> employees = employeesCrudRepository.getEmployeesMaxSalary();
		employees.stream()
				.forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getSalary()));
	}

}

