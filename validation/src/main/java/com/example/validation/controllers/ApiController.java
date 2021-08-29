package com.example.validation.controllers;

import com.example.validation.domains.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/add-user")
    public ResponseEntity post(@Valid @RequestBody User user, BindingResult bindingResult) {

        StringBuilder sb = new StringBuilder();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String errmsg = field.getDefaultMessage();

                System.out.println(field.getField());
                System.out.println(errmsg);

                sb.append(field.getField());
                sb.append(errmsg);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        //logic

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
