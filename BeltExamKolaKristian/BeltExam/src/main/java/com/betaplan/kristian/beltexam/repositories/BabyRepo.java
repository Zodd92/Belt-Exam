package com.betaplan.kristian.beltexam.repositories;

import com.betaplan.kristian.beltexam.models.Baby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BabyRepo extends CrudRepository<Baby, Long> {
    public abstract List<Baby> findAll();
}
