package com.example.facebook.mycontact.service;

import com.example.facebook.mycontact.domain.Block;
import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.interfaces.BlockRepository;
import com.example.facebook.mycontact.interfaces.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private PersonService personService;

    @Test
    public void blockFuncTest() {
        givenPeople();

        List<Person> people = personService.getPeopleExcludeBlockList();
        System.out.println(people);
    }

    private void givenPeople() {
        Person james = new Person ("james", 76);
        Person selly = new Person ("selly", 32);
        Person tom = new Person ("tom", 12);
        Person james2 = new Person ("james", 29);

        james.setBlock(blockRepository.save(new Block("james")));   //block repo 에 insert 된 이후에 person repo 에서 setBlock 을 할 수 있는 상황
        selly.setBlock(blockRepository.save(new Block("james")));

        personRepository.save(james);
        personRepository.save(selly);
        personRepository.save(tom);
        personRepository.save(james2);
    }

}