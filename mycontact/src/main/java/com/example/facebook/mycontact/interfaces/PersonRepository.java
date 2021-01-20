package com.example.facebook.mycontact.interfaces;

import com.example.facebook.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findByBlockIsNull();

    List<Person> findByBirthDayBetween(LocalDate startDate, LocalDate endDate);
}
