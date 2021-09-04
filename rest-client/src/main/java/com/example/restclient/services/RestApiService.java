package com.example.restclient.services;

import com.example.restclient.dtos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
public class RestApiService {

    public ResponseEntity requestRestApiServer() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
                .path("/api/server/get")
                .queryParam("name", "porori")
                .queryParam("age", 14)
                .encode()
                .build()
                .toUri();
        log.info("request uri : {}", uri);      // http://localhost:9090/api/server/get?name=porori&age=14

        RestTemplate restTemplate = new RestTemplate();

        //getForObject
        String resGetForObject = restTemplate.getForObject(uri, String.class);
        log.info("response getForObject : {}", resGetForObject);

        //getForEntity
        ResponseEntity<String> resGetForEntity = restTemplate.getForEntity(uri, String.class);
        HttpStatus httpStatus = resGetForEntity.getStatusCode();
        String responseBody = resGetForEntity.getBody();
        log.info("httpStatus getForEntity Str : {}", httpStatus);
        log.info("responseBody getForEntity Str: {}", responseBody);

        //getForEntity User
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity(uri, User.class);
        HttpStatus httpStatus1 = userResponseEntity.getStatusCode();
        User userResponseEntityBody = userResponseEntity.getBody();
        log.info("httpStatus getForEntity User : {}", httpStatus1);
        log.info("responseBody getForEntity User : {}", userResponseEntityBody);


        return ResponseEntity.status(HttpStatus.OK).body(userResponseEntityBody);
    }
}
