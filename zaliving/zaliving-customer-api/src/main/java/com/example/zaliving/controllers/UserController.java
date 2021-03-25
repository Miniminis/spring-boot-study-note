package com.example.zaliving.controllers;

import com.example.zaliving.domains.User;
import com.example.zaliving.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/user")
    public ResponseEntity<?> create(@RequestBody User user) throws URISyntaxException {
        userService.joinUser(user);

        URI location = new URI("/v1/user");
        return ResponseEntity.created(location).body("{}");
    }

    @GetMapping("/v1/user/{id}")
    public User detail(@PathVariable("id") Long id) {
        return userService.getDetail(id);
    }

}
