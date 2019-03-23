package com.example.demoSpringData.repositories;

import com.example.demoSpringData.model.Employees;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedEmployeesCrudRepository extends CrudRepository<Employees, Long> {

}
