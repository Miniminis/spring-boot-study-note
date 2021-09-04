package com.example.restclient.controller;

import com.example.restclient.services.RestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class RestApiController {

    private final RestApiService restApiService;

    @GetMapping("/get")
    public ResponseEntity request() {
        return restApiService.requestRestApiServer();
    }
}
