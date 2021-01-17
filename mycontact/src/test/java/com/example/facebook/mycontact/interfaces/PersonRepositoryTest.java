package com.example.facebook.mycontact.interfaces;

import com.example.facebook.mycontact.MycontactApplication;
import com.example.facebook.mycontact.domain.Block;
import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private PersonService personService;

    @Test
    @Transactional
    public void crud() {
//        Person person = new Person();
        Person person = new Person("james", 10);
//        person.setName("james");
//        person.setAge(10);
        personRepository.save(person);

        System.out.println(personRepository.findAll());

//        List<Person> personList =  personRepository.findAll();
//        assertEquals(personList.size(), 1);
//        assertEquals(personList.get(0).getName(), "james");
    }

    @Test
    @Transactional
    public void equalsAndHashCodeTest() {
        Person person1 = new Person("james", 10);
        Person person2 = new Person("james", 10);

        assertEquals(person1, person2);
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }

    @Test
    public void blockTest() {
//        Block block1 = new Block("martin", LocalDate.now(), LocalDate.now());
//        blockRepository.save(block1);
//        System.out.println(blockRepository.getOne(1L));

        generatePersonList();
        generateBlockList();

        System.out.println(personService.getPeopleExcludeBlockList());
    }

    public Person generatePerson(String name, int age) {
        return personRepository.save(new Person(name, age));
    }

    public Block generateBlock(String name) {
        return blockRepository.save(new Block(name, LocalDate.now(), LocalDate.now()));
    }

    public void generatePersonList() {
        generatePerson("martin", 10);
        generatePerson("james", 11);
        generatePerson("martin", 120);
        generatePerson("jenny", 23);
    }

    public void generateBlockList() {
        generateBlock("martin");
    }
}