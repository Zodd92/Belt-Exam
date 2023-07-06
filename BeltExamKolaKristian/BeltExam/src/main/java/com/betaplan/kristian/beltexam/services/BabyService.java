package com.betaplan.kristian.beltexam.services;

import com.betaplan.kristian.beltexam.models.Baby;
import com.betaplan.kristian.beltexam.repositories.BabyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BabyService {
    @Autowired
    BabyRepo babyRepo;

    public List<Baby> getAllBabies(){
        return babyRepo.findAll();
    }

    public void saveBaby(Baby baby){
        babyRepo.save(baby);
    }

    public Baby findBabyById(Long id){
        return babyRepo.findById(id).orElse(null);
    }
    public void delete(Baby baby){
        babyRepo.delete(baby);
    }
}
