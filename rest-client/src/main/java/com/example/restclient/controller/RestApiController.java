package com.example.restclient.controller;

import com.example.restclient.dtos.RequestDto;
import com.example.restclient.dtos.User;
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
    public ResponseEntity requestGet() {
        return restApiService.requestGetApiServer();
    }

    @GetMapping("/post")
    public ResponseEntity requestPost() {
        return restApiService.requestPostApiServer();
    }

    @GetMapping("/exchange")
    public ResponseEntity requestExchange() {
        return restApiService.requestExchangeApiServer();
    }

    @GetMapping("/generic")
    public ResponseEntity<RequestDto<User>> requestGeneric() {
        return restApiService.requestGenericReqApiServer();
    }

}
