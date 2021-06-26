package com.example.batch.sample.repositories;

import com.example.batch.sample.domains.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PersonRepositoryTest {

    private final PersonRepository personRepository;

    @Autowired
    public PersonRepositoryTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    void 데이터_삽입() {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Person person = Person.builder()
                    .name("아무개" + i)
                    .age(10 + i)
                    .authYn("N")
                    .createDate(LocalDateTime.now())
                    .build();

            persons.add(person);
        }

        personRepository.saveAll(persons);
    }
}