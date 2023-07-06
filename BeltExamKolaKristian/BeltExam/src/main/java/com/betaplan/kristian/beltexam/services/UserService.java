package com.betaplan.kristian.beltexam.services;

import com.betaplan.kristian.beltexam.models.LoginUser;
import com.betaplan.kristian.beltexam.models.User;
import com.betaplan.kristian.beltexam.repositories.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result){

        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());

        if(potentialUser.isPresent()){
            result.rejectValue("email","EmailTaken",
                    "the email has already been taken");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches",
                    "The Confirm Password must match Password!");
        }
        if(result.hasErrors()){
            return null;
        }
        else{
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }

    public User login(LoginUser newLogin, BindingResult result) {

        // TO-DO - Reject values:

        // Find user in the DB by email
        // Reject if NOT present
        Optional<User> userLookUp = userRepo.findByEmail(newLogin.getEmail());
        if (!userLookUp.isPresent()) {
            result.rejectValue("email", "MissingAccount", "No account found.");
            return null;
        }
        // User exists, retrieve user from DB
        User user = userLookUp.get();

        // Reject if BCrypt password match fails
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }

        // Return null if result has errors
        if(result.hasErrors()) {
            return null;
        }

        // Otherwise, return the user object
        return user;
    }

    public User findByEmail(String email) {

        Optional<User> result = userRepo.findByEmail(email);
        if(result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public User findById(Long id) {

        Optional<User> result = userRepo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }

        return null;
    }
    public User saveUser(User user){return userRepo.save(user);}
}
