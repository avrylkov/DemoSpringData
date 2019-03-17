package com.example.demoSpringData.repositories;

import com.example.demoSpringData.maps.Employees;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface CustomizedEmployeesCrudRepository extends CrudRepository<Employees, Long> {

    public Employees findFirstByLastName(String lastname);

}
