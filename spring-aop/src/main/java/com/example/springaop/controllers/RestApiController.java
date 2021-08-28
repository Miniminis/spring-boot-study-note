package com.example.springaop.controllers;

import com.example.springaop.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @GetMapping("/get/{id}")
    public String requestGet(@PathVariable Long id, @RequestParam String name) {
        return id + " : " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return user;
    }

}
