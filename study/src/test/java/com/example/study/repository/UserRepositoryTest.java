package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired      //의존성 주입(Dependency Injection - DI), singleton 기반
    UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
        user.setAccount("Tester1");
        user.setPassword("1234");
        user.setStatus("U");
        user.setEmail("Tester1@test.com");
        user.setRegisteredAt(LocalDateTime.now());
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestServer");

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
        System.out.println(newUser);        //lombok @Data's toString()
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> selectedUser = userRepository.findById(8L);  //Optional : 있을수도 있고, 없을수도 있다.

//        selectedUser.ifPresent(user -> {
//            System.out.println(user);
//        });

//        selectedUser.ifPresent(System.out::println);
        /*Hibernate: select user0_.id as id1_0_0_, user0_.account as account2_0_0_, user0_.created_at as created_3_0_0_, user0_.created_by as created_4_0_0_, user0_.email as email5_0_0_, user0_.phone_number as phone_nu6_0_0_, user0_.updated_at as updated_7_0_0_, user0_.updated_by as updated_8_0_0_ from user user0_ where user0_.id=?
        User(id=2, account=Tester02, email=Tester02@test.com, phoneNumber=010-7772-8888, createdAt=2020-12-30T00:00, createdBy=Tester02, updatedAt=null, updatedBy=null)*/

//        selectedUser.ifPresent(user -> {
//            user.getOrderDetails().stream().forEach(orderDetail -> {
//                System.out.println(orderDetail.getItem());
//            });
//        });

        //Hibernate: select user0_.id as id1_2_0_, user0_.account as account2_2_0_, user0_.created_at as created_3_2_0_, user0_.created_by as created_4_2_0_, user0_.email as email5_2_0_, user0_.phone_number as phone_nu6_2_0_, user0_.updated_at as updated_7_2_0_, user0_.updated_by as updated_8_2_0_ from user user0_ where user0_.id=?
        //failed to lazily initialize a collection of role: com.example.study.model.User.orderDetails, could not initialize proxy - no Session
    }

    @Test
    @Transactional
    public void readWithParam() {
        Optional<User> user = userRepository.findByAccount("Tester153");
//        Assertions.assertTrue(user.isPresent());
        user.ifPresent(mUser -> {
            System.out.println("*************************"+mUser);
        });
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
