package com.example.interceptor.controllers;


import com.example.interceptor.annotations.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/private")
    @Auth
    public String requestPrivateMsg() {
        return "private hello!";
    }

    @GetMapping("/public")
    public String requestPublicMsg() {
        return "public hello!";
    }

    @GetMapping("/query-auth")
    public String requestQueryAuthMsg() {
        return "queryAuth hello!";
    }

}
