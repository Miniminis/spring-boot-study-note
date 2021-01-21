package com.example.facebook.mycontact.interfaces;

import com.example.facebook.mycontact.domain.Person;
import com.example.facebook.mycontact.domain.dto.Birthday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findByBlockIsNull();

    List<Person> findByBirthDayBetween(Birthday startDate, Birthday endDate);

    List<Person> findByBirthDayMonthOfBirthday(int month);
}
