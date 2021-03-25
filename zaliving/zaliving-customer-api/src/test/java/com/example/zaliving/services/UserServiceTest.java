package com.example.zaliving.services;

import com.example.zaliving.domains.User;
import com.example.zaliving.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void initMock() {
        MockitoAnnotations.openMocks(this);
        User createdUser = User.builder()
                .name("jerry")
                .email("jerry@holostanding.com")
                .build();

        given(userRepository.save(any())).willReturn(createdUser);
    }

    @Test
    void 회원가입_서비스() {
        User user = User.builder()
                .name("jerry")
                .email("jerry@holostanding.com")
                .build();

        User addedUser = userService.joinUser(user);

        assertThat(addedUser.getName()).isEqualTo(user.getName());
        assertThat(addedUser.getEmail()).isEqualTo(user.getEmail());

        verify(userRepository).save(any());
    }

}