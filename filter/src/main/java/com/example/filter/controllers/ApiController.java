package com.example.filter.controllers;

import com.example.filter.dtos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j      //Lombok lib
@RestController
@RequestMapping("/api/user")
public class ApiController {

    @PostMapping("")
    public User requestPost(@RequestBody User user) {
        log.info("User : {}, {} ", user, user);

        return user;
    }

}
