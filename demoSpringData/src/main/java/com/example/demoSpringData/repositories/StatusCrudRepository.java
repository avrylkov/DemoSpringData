package com.example.demoSpringData.repositories;

import com.example.demoSpringData.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusCrudRepository extends CrudRepository<Status, Long> {

}
