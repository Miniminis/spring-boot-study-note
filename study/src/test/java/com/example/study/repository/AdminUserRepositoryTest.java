package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("TestAdmin3");
        adminUser.setPassword("3333");
        adminUser.setStatus("W");
        adminUser.setRole("Admin");
        adminUser.setRegisteredAt(LocalDateTime.now());
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("TestServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);
    }
}
