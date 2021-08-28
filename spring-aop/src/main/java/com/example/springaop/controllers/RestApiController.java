package com.example.springaop.controllers;

import com.example.springaop.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @GetMapping("/get/{id}")
    public String requestGet(@PathVariable Long id, @RequestParam String name) {
        System.out.println("id : " + id);
        System.out.println("name : " + name);

        return id + " : " + name;

        /** log
         * id : 1
         * name : steve
         * */
    }

    @PostMapping("/post")
    public void post(@RequestBody User user) {
        System.out.println(user);

        /** log
         * User(id=1, name=steeeeeeeeve, email=steve@gmail.com)
         * */
    }

}
