package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping("/postMethod")     //http://localhost:8080/api/postMethod
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }

    @PutMapping("/putMethod")
    public String putMethod() {
        return "";
    }

    @PatchMapping("/patchMethod")
    public String patchMethod() {
        return "";
    }

    @DeleteMapping("/deleteMethod")
    public String deleteMathod () {
        return "";
    }
}
