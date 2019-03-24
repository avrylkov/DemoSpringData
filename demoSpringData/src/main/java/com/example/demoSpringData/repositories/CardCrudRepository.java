package com.example.demoSpringData.repositories;

import com.example.demoSpringData.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCrudRepository extends CrudRepository<Card, Long> {

}
