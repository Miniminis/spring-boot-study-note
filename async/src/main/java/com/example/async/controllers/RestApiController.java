package com.example.async.controllers;

import com.example.async.services.RestApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private final RestApiService restApiService;

    public RestApiController(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    @GetMapping("/async-annotation")
    public String requestAsyncAnnotation() {
        restApiService.requestAsync();
        return "hello @async";
    }

    @GetMapping("/normal")
    public String requestNormalAnnotation() {
        restApiService.requestNormal();
        return "hello world!";
    }
}
