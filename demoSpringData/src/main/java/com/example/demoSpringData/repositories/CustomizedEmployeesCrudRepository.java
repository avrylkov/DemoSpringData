package com.example.demoSpringData.repositories;

import com.example.demoSpringData.model.Employees;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomizedEmployeesCrudRepository extends CrudRepository<Employees, Long>, CustomizedEmployees<Employees> {

    Optional<Employees> findByFirstNameAndAndLastName(String firstName, String lastName);
    List<Employees> findFirst5ByFirstNameStartsWithOrderByFirstName(String firstNameStartsWith);
}
