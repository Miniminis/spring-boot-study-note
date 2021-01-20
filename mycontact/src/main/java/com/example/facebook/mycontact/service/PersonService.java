package com.example.facebook.mycontact.service;

import com.example.facebook.mycontact.domain.Block;
import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.interfaces.BlockRepository;
import com.example.facebook.mycontact.interfaces.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlockList() {
        List<Person> personList = personRepository.findAll();
        return personList.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
        //이렇게 설정할 경우에, 동명이인의 martin 에 대해서 2명이 동시에 block 되는 현상이 발생됨.
        //--> person 과 관계설정이 필요한 대목!
    }

    @Transactional(readOnly = true)
    public void getPerson(Long id) {
        Person person = personRepository.findById(id).get();
        log.info("{}", person);
    }
}
