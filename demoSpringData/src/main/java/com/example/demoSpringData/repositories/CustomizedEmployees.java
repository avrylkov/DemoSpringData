package com.example.demoSpringData.repositories;

import java.util.List;

public interface CustomizedEmployees<T> {

    void delete(T entity);

    List<T> getEmployeesMaxSalary();

}
