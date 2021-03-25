package com.example.zaliving.services;

import com.example.zaliving.domains.User;
import com.example.zaliving.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User joinUser(User user) {
        return userRepository.save(user);
    }

    public User getDetail(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User authenticate(String email, String password) {
        return userRepository.findByEmail(email);
    }

    public void saveAuthToken(User user, String authToken) {
        user.setAuthToken(authToken);
        userRepository.save(user);
    }
}
