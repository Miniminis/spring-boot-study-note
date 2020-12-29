package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired      //의존성 주입(Dependency Injection - DI), singleton 기반
    UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
//        user.setId();     //AI
        user.setAccount("Tester03");
        user.setEmail("Tester03@test.com");
        user.setPhoneNumber("010-7773-8888");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Tester03");

        User newUser = userRepository.save(user);
        System.out.println(newUser);        //lombok @Data's toString()
    }

    @Test
    public void read() {
        Optional<User> selectedUser = userRepository.findById(2L);  //Optional : 있을수도 있고, 없을수도 있다.

//        selectedUser.ifPresent(user -> {
//            System.out.println(user);
//        });

        selectedUser.ifPresent(System.out::println);

        /*Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
        User(id=2, account=Tester02, email=Tester02@test.com, phoneNumber=010-7772-8888, createdAt=2020-12-30T00:00, createdBy=Tester02, updatedAt=null, updatedBy=null)*/
    }

    @Test
    @Transactional
    public void update() {
        Optional<User> selectedUser = userRepository.findById(1L);
        selectedUser.ifPresent(user -> {
            user.setAccount("ChangedTester0123");
            user.setUpdatedAt(LocalDateTime.now());
            user.setUpdatedBy("Update() Method");

            userRepository.save(user);
        });

        /*Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
        Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
        Hibernate: update user set account=?, created_at=?, created_by=?, email=?, phone_number=?, updated_at=?, updated_by=? where id=?*/
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> selectedUser = userRepository.findById(1L);

        Assertions.assertTrue(selectedUser.isPresent());        //true

        selectedUser.ifPresent(user -> {
            userRepository.delete(user);
        });

        Assertions.assertTrue(selectedUser.isPresent());        //false

//        Optional<User> deletedUser = userRepository.findById(2L);
//        System.out.println(deletedUser.isPresent());
    }

    /*Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
    Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
    Hibernate: delete from user where id=?
    Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
    false
*/
}
