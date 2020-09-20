package com.fastcampus.javaallinone.project3.mycontact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Martin
 * @since 2019-07-01
 */
@RestController
public class HelloWorldController {
    @GetMapping(value = "/api/helloWorld")
    public String helloWorld() {
        return "HelloWorld";
    }

    @GetMapping(value = "/api/helloException")
    public String helloException() {
        throw new RuntimeException("Hello RuntimeException");
    }
}
