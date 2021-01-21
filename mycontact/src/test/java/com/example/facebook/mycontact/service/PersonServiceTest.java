package com.example.facebook.mycontact.service;

import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.interfaces.BlockRepository;
import com.example.facebook.mycontact.interfaces.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void fetchTest() {
        Person result = personService.getPerson(1L);
        assertEquals(result.getName(), "martin");
    }

    @Test
    public void getPeopleExcludeBlockList() {
        List<Person> result = personService.getPeopleExcludeBlockList();

        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getName(), "james");
        assertEquals(result.get(1).getName(), "katy");
        assertEquals(result.get(2).getName(), "martin");
    }

}