package com.blogapp12.controller;

import com.blogapp12.entity.User;
import com.blogapp12.payload.LoginDto;
import com.blogapp12.payload.Singnup;
import com.blogapp12.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //http://localhost:8080/api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody Singnup singnup){


        if(userRepository.existsByName(singnup.getName())){
            return new ResponseEntity<>("name is already exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(userRepository.existsByEmail(singnup.getEmail())){
            return new ResponseEntity<>("email is already exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }


        User user = new User();

        user.setEmail(singnup.getEmail());
        user.setName(singnup.getName());
        user.setPassword(passwordEncoder.encode(singnup.getPassword()));
        user.setUsername(singnup.getUsername());

        userRepository.save(user);
        return new ResponseEntity<>("user registered",HttpStatus.CREATED);

    }

    public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto){
        return null;
    }
}
