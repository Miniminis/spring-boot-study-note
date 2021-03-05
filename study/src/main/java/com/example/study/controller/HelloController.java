package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/hello")     //localhost:8080/hello
public class HelloController {

    @GetMapping("")
    public String getParameter() {
        return "index";
    }
}
