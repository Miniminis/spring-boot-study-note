package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //아직 서비스 생성 전이므로, testCode 를 통해서 테스트 진행

    // select * from user where account = ?
    Optional<User> findByAccount(String account);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
