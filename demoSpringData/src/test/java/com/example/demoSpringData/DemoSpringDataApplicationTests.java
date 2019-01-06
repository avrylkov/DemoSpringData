package com.example.demoSpringData;

import com.example.demoSpringData.model.Employees;
import com.example.demoSpringData.repositories.CustomizedEmployeesCrudRepository;
import com.example.demoSpringData.repositories.EmployeesBaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DemoSpringDataApplication.class })
public class DemoSpringDataApplicationTests {

	@Autowired
    private CustomizedEmployeesCrudRepository employeesCrudRepository;
	@Resource
	private EmployeesBaseRepository employeesBaseRepository;

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
	public void testFindByFirstNameAndLastName() {
		Optional<Employees> employeesOptional = employeesCrudRepository.findByFirstNameAndLastName("Alex", "Ivanov");
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
	public void testFindByFirstNameStartsWithOrderByFirstNamePage() {
		List<Employees> list = employeesCrudRepository
				.findByFirstNameStartsWith("A", PageRequest.of(2,3, Sort.by("firstName")));
		list.forEach(e -> System.out.println(e.getFirstName() + " " +e.getLastName()));
	}

	@Test
	@Transactional
	@Commit
	public void testDeleteEmployees() {
		Optional<Employees> employeesOptional = employeesCrudRepository.findByFirstNameAndLastName("Alex", "Ivanov");
		employeesOptional.ifPresent(employeesCrudRepository::delete);
	}

	@Test
	@Transactional
	public void testMaxSalaryEmployees() {
		List<Employees> employees = employeesCrudRepository.getEmployeesMaxSalary();
		employees.stream()
				.forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getSalary()));
	}

	@Test
	@Transactional
	@Commit
	public void testBaseRepository() {
		Employees employees = new Employees();
		employees.setLastName("Ivanov");

		Example<Employees> example = Example.of(employees);
		Optional<Employees> employeesOptional = employeesBaseRepository.findOne(example);
		employeesOptional.ifPresent(employeesBaseRepository::delete);
	}

	@Test
	@Transactional
	public void testFindEmployeesWithMoreThanSalary() {
		List<Employees> employees = employeesCrudRepository.findEmployeesWithMoreThanSalary(10000L, Sort.by("lastName"));
		employees.stream()
				.forEach(e -> System.out.println(e.getLastName() + " " + e.getFirstName() + " " + e.getSalary()));
	}

}

