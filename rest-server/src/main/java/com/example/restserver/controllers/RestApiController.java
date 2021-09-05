package com.example.restserver.controllers;

import com.example.restserver.dtos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/post/name/{name}/age/{age}")
    public ResponseEntity post(@PathVariable String name,
                               @PathVariable int age,
                               @RequestBody User user) {
        log.info("rest-server post called");
        log.info("name : {}", name);
        log.info("age : {}", age);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/exchange/name/{name}/age/{age}")
    public ResponseEntity exchange(@PathVariable String name,
                                   @PathVariable int age,
                                   @RequestBody User user,
                                   @RequestHeader("x-authorization") String authorization,
                                   @RequestHeader("custom-header") String customHeader) {

        log.info("rest-server post called");
        log.info("name : {}", name);
        log.info("age : {}", age);

        log.info("header 1 : {}", authorization);
        log.info("header 2 : {}", customHeader);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
