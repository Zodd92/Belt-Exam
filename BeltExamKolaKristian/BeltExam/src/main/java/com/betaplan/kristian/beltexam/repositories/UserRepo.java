package com.betaplan.kristian.beltexam.repositories;

import com.betaplan.kristian.beltexam.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long Id);


}