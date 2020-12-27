package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")     //localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")        //localhost:8080/api/getMethod
    public String getRequest() {
        return "Hi, Client!";
    }

    @GetMapping("/getParameter")        //localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        String password = "defaultPassword";

        System.out.println(id);
        System.out.println(pwd);

        return id + pwd;
    }

    @GetMapping("/getMultiParameter")       //localhost:8080/api/getMultiParameter?account=guest&email=test@test.com&page=10
    public SearchParam getMultiParameter(SearchParam searchParam) {
        return searchParam;
        //{"account":"guest","email":"test@test.com","page":10} 자동으로 json type 으로 return!!!!!
    }
    
}
