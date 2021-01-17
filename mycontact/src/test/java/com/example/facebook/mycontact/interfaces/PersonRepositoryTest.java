package com.example.facebook.mycontact.interfaces;

import com.example.facebook.mycontact.MycontactApplication;
import com.example.facebook.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest{

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Transactional
    public void crud() {
        Person person = new Person();
        person.setName("james");
        person.setAge(10);
        personRepository.save(person);

        System.out.println(personRepository.findAll());

        List<Person> personList =  personRepository.findAll();
        assertEquals(personList.size(), 1);
        assertEquals(personList.get(0).getName(), "james");
    }
}