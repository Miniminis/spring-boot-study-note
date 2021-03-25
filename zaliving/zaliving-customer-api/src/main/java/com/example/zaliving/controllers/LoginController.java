package com.example.zaliving.controllers;

import com.example.zaliving.domains.RequestDtoLogin;
import com.example.zaliving.domains.ResponseDtoLogin;
import com.example.zaliving.domains.User;
import com.example.zaliving.services.UserService;
import com.example.zaliving.utils.JwtUtil;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping("/v1/login")
    public ResponseEntity<ResponseDtoLogin> login(@RequestBody RequestDtoLogin resource) throws URISyntaxException {
        User user = userService.authenticate(resource.getEmail(), resource.getPassword());

        //검증된 회원
        JwtUtil jwtUtil = new JwtUtil("12345678901234567890123456789012345678901234567890");

        String authToken = jwtUtil.createToken(user.getEmail(), user.getName());
        userService.saveAuthToken(user, authToken);

        String url = "/v1/login";
        return ResponseEntity.created(new URI(url)).body(ResponseDtoLogin.builder().authToken(authToken).build());
    }
}
