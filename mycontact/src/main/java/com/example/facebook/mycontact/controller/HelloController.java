package com.example.facebook.mycontact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController     //Controller + ResponseBody
public class HelloController {

    @GetMapping("/api/helloworld")
    public String helloWorld() {
        return "Hello, SpringBoot!";
    }
}
