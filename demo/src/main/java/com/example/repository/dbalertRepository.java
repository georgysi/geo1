package com.example.repository;

import com.example.dao.entity.dbalert;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * AlertCategoryRepository
 * CRUD repository for {@link dbalert} entity basic DB operations
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
@Repository
public interface dbalertRepository extends CrudRepository<dbalert, Integer> {

    Optional<dbalert> findByGeneralAlertId(Integer AlertId);
    //Optional<dbalert> findById(Integer AlertId);

}
