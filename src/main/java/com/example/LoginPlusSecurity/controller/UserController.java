package com.example.LoginPlusSecurity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginPlusSecurity.model.User;
import com.example.LoginPlusSecurity.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserDetails(@PathVariable String username) {
        Optional<User> user = userService.getUserByName(username);
        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity("User does not exist...",HttpStatus.NOT_FOUND);
        }
    }
}
