package com.example.study.repository;

import com.example.study.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //아직 서비스 생성 전이므로, testCode 를 통해서 테스트 진행
}
