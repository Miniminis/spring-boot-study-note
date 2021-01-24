package com.example.facebook.mycontact.controller;

import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person readPerson(@PathVariable Long id) {
        log.info("{}", id);
        return personService.getPerson(id);
    }


}
