package com.example.demoSpringData.repositories;

import com.example.demoSpringData.model.Employees;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesEntityRepository extends ParentEntityRepository <Employees> {
}
