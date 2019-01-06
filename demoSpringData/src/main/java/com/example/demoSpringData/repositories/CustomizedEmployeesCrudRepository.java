package com.example.demoSpringData.repositories;

import com.example.demoSpringData.model.Employees;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomizedEmployeesCrudRepository extends CrudRepository<Employees, Long>, CustomizedEmployees<Employees> {

    Optional<Employees> findByFirstNameAndLastName(String firstName, String lastName);
    List<Employees> findFirst5ByFirstNameStartsWithOrderByFirstName(String firstNameStartsWith);
    List<Employees> findByFirstNameStartsWith(String firstNameStartsWith, Pageable page);

    @Query("select e from Employees e where e.salary > :salary")
    List<Employees> findEmployeesWithMoreThanSalary(@Param("salary") Long salary, Sort sort);
}
