package com.example.facebook.mycontact.interfaces;

import com.example.facebook.mycontact.domain.Block;
import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.domain.dto.Birthday;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;


    void givenPerson(String name, int age, String bloodType) {
        givenPerson(name, age, bloodType, null);
    }


    void givenPerson(String name, int age, String bloodType, Block block) {
        givenPerson(name, age, bloodType, block, null);
    }

    void givenPerson(String name, int age, String bloodType, Block block, LocalDate birthDay) {
        Person person = new Person(name, age);
        person.setBloodType(bloodType);
        person.setBlock(block);
        person.setBirthDay(new Birthday(birthDay));

        personRepository.save(person);
    }

    @Test
    @Transactional
    void findByMonthOfBirthDay() {
        givenPerson("martin", 10, "A", new Block("james"),
                LocalDate.of(1994, 8, 11));
        givenPerson("james", 12, "B", null,
                LocalDate.of(1990, 12, 19));
        givenPerson("selly", 38, "AB", new Block("james"),
                LocalDate.of(1988, 8, 22));
        givenPerson("martin", 120, "A", null,
                LocalDate.of(1993, 5, 24));

        List<Person> personList = personRepository.findByBirthDayMonthOfBirthday(8);
        personList.forEach(System.out::println);
    }


    @Test
    @Transactional
    void findByBirthDateBetweenTest() {
        givenPerson("martin", 10, "A", new Block("james"),
                LocalDate.of(1994, 8, 11));
        givenPerson("james", 12, "B", null,
                LocalDate.of(1990, 12, 19));
        givenPerson("selly", 38, "AB", new Block("james"),
                LocalDate.of(1988, 8, 22));
        givenPerson("martin", 120, "A", null,
                LocalDate.of(1993, 5, 24));

        List<Person> personList = personRepository.findByBirthDayBetween(LocalDate.of(1988, 8, 1),
                LocalDate.of(1994, 8, 31));

        personList.forEach(System.out::println);
    }


    @Test
    void findByBlockIsNullTest() {
        givenPerson("martin", 10, "A", new Block("james"));
        givenPerson("james", 12, "B");
        givenPerson("selly", 38, "AB", new Block("james"));
        givenPerson("martin", 120, "A");

        List<Person> personList = personRepository.findByBlockIsNull();
        personList.forEach(System.out::println);
    }


    @Test
    void findByNameTest() {
        givenPerson("martin", 10, "A");
        givenPerson("james", 12, "B");
        givenPerson("selly", 38, "AB");
        givenPerson("martin", 120, "A");

        List<Person> personList = personRepository.findByName("martin");
        personList.forEach(System.out::println);
    }


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
}