package com.example.demoSpringData.repositories;

import com.example.demoSpringData.maps.Employees;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomizedEmployeesCrudRepository extends CrudRepository<Employees, Long>,
        QuerydslPredicateExecutor<Employees> {

}
