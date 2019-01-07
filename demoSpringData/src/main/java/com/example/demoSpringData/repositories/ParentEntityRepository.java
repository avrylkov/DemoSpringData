package com.example.demoSpringData.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface ParentEntityRepository<T> extends Repository<T, Long> {

    @Query("select t from #{#entityName} t where t.deleted = ?1")
    List<T> findMarked(Boolean deleted);
}
