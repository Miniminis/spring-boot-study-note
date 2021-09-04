package com.example.restserver.controllers;

import com.example.restserver.dtos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
@Slf4j
public class RestApiController {

    @GetMapping("/get")
    public ResponseEntity get(@RequestParam String name, @RequestParam int age) {
        log.info("rest-server get called");

        User user = new User();
        user.setName(name);
        user.setAge(age);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
