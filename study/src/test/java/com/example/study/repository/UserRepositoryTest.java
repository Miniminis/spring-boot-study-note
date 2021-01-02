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
//        User user = new User();
//        user.setAccount("Tester2");
//        user.setPassword("5678");
//        user.setStatus("U");
//        user.setEmail("Tester2@test.com");
//        user.setRegisteredAt(LocalDateTime.now());
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("TestServer");

        /* Builder Pattern */
        User user = User.builder()
                .account("Tester4")
                .password("4444")
                .status("R")
                .email("Tester4@gmail.com")
                .registeredAt(LocalDateTime.now())
//                .createdAt(LocalDateTime.now())
//                .createdBy("TestServer")
                .build();

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> selectedUser = userRepository.findById(1L);  //Optional : 있을수도 있고, 없을수도 있다.

        selectedUser.ifPresent(user -> {
            user.getOrderGroups().stream().forEach(orderGroup -> {
                System.out.println("===============Order Group==================");
                System.out.println(orderGroup.getPaymentType());
                System.out.println(orderGroup.getOrderAt());

                System.out.println("===============Order Detail==================");
                orderGroup.getOrderDetails().stream().forEach(orderDetail -> {
                    System.out.println(orderDetail.getId());
                    System.out.println(orderDetail.getTotalPrice());

                    System.out.println("===============Order Item==================");
                    System.out.println(orderDetail.getItem().getBrandName());
                    System.out.println(orderDetail.getItem().getPrice());

                    System.out.println("===============Partner ==================");
                    System.out.println(orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println(orderDetail.getItem().getPartner().getCeoName());

                    System.out.println("===============Category ==================");
                    System.out.println(orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println(orderDetail.getItem().getPartner().getCategory().getType());
                });

            });
        });
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
//    @Transactional
    public void update() {
        Optional<User> selectedUser = userRepository.findByAccount("Tester1");
        selectedUser.ifPresent(user -> {
//            user.setAccount("ChangedTester0123");
//            user.setUpdatedAt(LocalDateTime.now());
//            user.setUpdatedBy("Update() Method");

            /* Accessors Chain Pattern */
            user.setAccount("Changed3");
//                    .setUpdatedAt(LocalDateTime.now())
//                    .setUpdatedBy("TestServer");

            userRepository.save(user);
        });
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
