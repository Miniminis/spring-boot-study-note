package com.example.springaop.controllers;

import com.example.springaop.annotations.Decoder;
import com.example.springaop.annotations.Timer;
import com.example.springaop.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @GetMapping("/get/{id}")
    public String requestGet(@PathVariable Long id, @RequestParam String name) {
        return id + " : " + name;
    }

    @PostMapping("/post")
    public User requestPost(@RequestBody User user) {
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void requestDelete() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Decoder
    @PutMapping("/put")
    public User requestPut(@RequestBody User user) {
        return user;
    }

}
