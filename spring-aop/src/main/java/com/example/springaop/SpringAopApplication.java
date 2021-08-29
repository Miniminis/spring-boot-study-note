package com.example.springaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootApplication
public class SpringAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
        System.out.println(Base64.getEncoder().encodeToString("tester1234@gmail.com".getBytes(StandardCharsets.UTF_8)));
        //dGVzdGVyMTIzNEBnbWFpbC5jb20=
    }

}
