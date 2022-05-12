package com.example.blueberry.controllers;


import com.example.blueberry.models.User;
import com.example.blueberry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
