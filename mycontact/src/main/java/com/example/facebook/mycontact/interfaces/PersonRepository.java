package com.example.facebook.mycontact.interfaces;

import com.example.facebook.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
