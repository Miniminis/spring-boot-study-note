package com.example.facebook.mycontact.interfaces;

import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.domain.dto.Birthday;
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

    @Autowired
    private BlockRepository blockRepository;


    @Test
    void findByMonthOfBirthDay() {
        List<Person> result = personRepository.findByBirthDayMonthOfBirthday(8);

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "martin");
        assertEquals(result.get(1).getName(), "james");
    }


    @Test
    void findByBirthDateBetweenTest() {
        List<Person> result = personRepository.findByBirthDayBetween(
                new Birthday(LocalDate.of(1988, 8, 1)),
                new Birthday(LocalDate.of(1994, 8, 31)));


        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "martin");
        assertEquals(result.get(1).getName(), "katy");
    }


    @Test
    void findByBlockIsNullTest() {
        List<Person> result  = personRepository.findByBlockIsNull();
        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getName(), "james");
        assertEquals(result.get(1).getName(), "katy");
        assertEquals(result.get(2).getName(), "martin");
    }


    @Test
    void findByNameTest() {
        List<Person> result = personRepository.findByName("martin");
        assertEquals(result.size(), 2);
    }


    @Test
    public void crud() {
        Person person = new Person();
        person.setName("tom");
        person.setAge(120);

        personRepository.save(person);

        List<Person> result =  personRepository.findByName("tom");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "tom");
    }

    @Test
    public void equalsAndHashCodeTest() {
        Person person1 = new Person("james", 10);
        Person person2 = new Person("james", 10);

        assertEquals(person1, person2);
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    @Transactional
    public Person read(Long id) {
        return personRepository.findById(id)
                .orElse(null);
    }


    @Test
    @Transactional
    public void casecadeMergeTest() {
        Person result = read(1L);
        result.getBlock().setStartDate(LocalDate.now()).setReason("Too much talker");
        personRepository.save(result);

        Person person = read(1L);
        assertEquals(person.getName(), "martin");
        assertEquals(person.getBlock().getReason(), "Too much talker");
    }


    @Test
    @Transactional
    public void casecadeRemoveTest() {      //CascadeType.REMOVE
        Person person = read(1L);
        personRepository.delete(person);
        assertNull(read(1L));
    }


    @Test
    @Transactional
    public void casecadeOrphanRemovalTest() {       //orphanRemoval = true
        Person person = read(1L);
        person.setBlock(null);
        personRepository.save(person);
        assertNull(read(1L).getBlock());
    }
}