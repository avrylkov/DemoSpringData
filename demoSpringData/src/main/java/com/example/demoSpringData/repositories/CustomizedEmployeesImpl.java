package com.example.demoSpringData.repositories;

import com.example.demoSpringData.model.Employees;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedEmployeesImpl implements CustomizedEmployees {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void delete(Object entity) {
        Employees employees = (Employees) entity;
        employees.setDeleted(true);
        em.persist(employees);
    }

    @Override
    public List getEmployeesMaxSalary() {
        return em.createQuery("from Employees where salary = (select max(salary) from Employees )", Employees.class)
                .getResultList();
    }


}
