package com.example.restclient.services;

import com.example.restclient.dtos.RequestDto;
import com.example.restclient.dtos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.net.URI;

@Service
@Slf4j
public class RestApiService {

    public ResponseEntity requestGetApiServer() {
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

    public ResponseEntity requestPostApiServer() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090/api/server")
                .path("/post/name/{name}/age/{age}")
                .encode()
                .build()
                .expand("hong", 34)
                .toUri();
        log.info("Uri : {}", uri);

        User user = new User();
        user.setName("Hong gil dong");
        user.setAge(12);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.postForEntity(uri, user, User.class);

        log.info("statusCode : {}", response.getStatusCode());
        log.info("headers : {}", response.getHeaders());
        log.info("body : {}", response.getBody());

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }

    public ResponseEntity requestExchangeApiServer() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090/api/server")
                .path("/exchange/name/{name}/age/{age}")
                .encode()
                .build()
                .expand("hong", 34)
                .toUri();
        log.info("Uri : {}", uri);

        User user = new User();
        user.setName("Hong gil dong");
        user.setAge(12);

        RequestEntity<User> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "AUTHORIZED")
                .header("custom-header", "hello~")
                .body(user);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(requestEntity, User.class);

        log.info("statusCode : {}", response.getStatusCode());
        log.info("headers : {}", response.getHeaders());
        log.info("body : {}", response.getBody());

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }

    public ResponseEntity<RequestDto<User>> requestGenericReqApiServer() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090/api/server")
                .path("/exchange/name/{name}/age/{age}")
                .encode()
                .build()
                .expand("hong", 34)
                .toUri();
        log.info("Uri : {}", uri);

        User user = new User();
        user.setName("Hong gil dong");
        user.setAge(12);

        RequestDto<User> requestDto = new RequestDto<>();
        requestDto.setHeader(new RequestDto.Header(200));
        requestDto.setReqBody(user);


        RequestEntity<RequestDto<User>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "AUTHORIZED")
                .header("custom-header", "hello~")
                .body(requestDto);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RequestDto<User>> response = restTemplate.exchange(
                requestEntity,
                new ParameterizedTypeReference<RequestDto<User>>(){});

        log.info("statusCode : {}", response.getStatusCode());
        log.info("headers : {}", response.getHeaders());
        log.info("body : {}", response.getBody());

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());

    }
}
