package com.example.facebook.mycontact.service;

import com.example.facebook.mycontact.domain.Block;
import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.interfaces.BlockRepository;
import com.example.facebook.mycontact.interfaces.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public void fetchTest() {
        givenPeople();
        personService.getPerson(1L);
    }

    @Test
    public void blockFuncTest() {
        givenPeople();

        /*block list 가 없는 사람들의 목록 조회*/
//        List<Person> people = personService.getPeopleExcludeBlockList();
//        System.out.println(people);

        /*모든 사람들 목록 조회*/
        List<Person> people = personRepository.findAll();
        people.forEach(System.out::println);

        /*@OneToOne(cascade = CascadeType.MERGE)*/
        Person person = people.get(1);
        person.getBlock().setStartDate(LocalDate.now()).setReason("Too much talker");
        personRepository.save(person);

        personRepository.findAll().forEach(System.out::println);

        /*CascadeType.REMOVE*/
//        personRepository.delete(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);

        /*orphanRemoval = true*/
        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

    private void givenPeople() {
        Person james = new Person ("james", 76);
        Person selly = new Person ("selly", 32);
//        Person tom = new Person ("tom", 12);
//        Person james2 = new Person ("james", 29);

        /*block repo 에 insert 된 이후에 person repo 에서 setBlock 을 할 수 있는 상황*/
//        james.setBlock(blockRepository.save(new Block("james")));
//        selly.setBlock(blockRepository.save(new Block("james")));

        /*Relation 간의 영속성 설정 이후 : @OneToOne(cascade = CascadeType.PERSIST)
        Hibernate: insert into block (end_date, name, reason, start_date, id) values (?, ?, ?, ?, ?)
        Hibernate: insert into person (address, age, birth_day, block_id, blood_type, job, name, phone_number, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
        * */
        james.setBlock(new Block("james"));
        selly.setBlock(new Block("james"));

        personRepository.save(james);
        personRepository.save(selly);
//        personRepository.save(tom);
//        personRepository.save(james2);
    }

}