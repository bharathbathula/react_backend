package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserRepository userRepo;

    @GetMapping("/demo")
    String demo() {
        return "demo";
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        Users u = this.userRepo.findByEmail(user.getEmail());
        if (u != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("EMAIL already exists");
        }
        this.userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

}
