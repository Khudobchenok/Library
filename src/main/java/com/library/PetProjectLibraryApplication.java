package com.library;

import com.library.entity.User;
import com.library.repository.UserRepo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value ="", produces = "application/json")
public class PetProjectLibraryApplication {

    @Autowired
    private UserRepo userRepo;

    public static void main(String[] args) {
        SpringApplication.run(PetProjectLibraryApplication.class, args);
    }

    @RequestMapping("createUser")
    public User createUser(@RequestParam(value = "login") final String login,
                           @RequestParam(value = "password") final String password) {
        return userRepo.save(new User(login, password));
    }
}
