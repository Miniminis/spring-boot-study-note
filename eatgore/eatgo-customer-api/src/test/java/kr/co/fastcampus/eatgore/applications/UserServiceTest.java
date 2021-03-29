package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.domains.exceptions.EmailDoesNotExistedException;
import kr.co.fastcampus.eatgore.domains.exceptions.EmailExistedException;
import kr.co.fastcampus.eatgore.domains.exceptions.WrongPasswordException;
import kr.co.fastcampus.eatgore.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void initMock() {
        MockitoAnnotations.openMocks(this);
        mockUserRepository();
    }

    private void mockUserRepository() {
        User user = User.builder()
                .id(1L)
                .name("TESTER")
                .email("tester@test.com")
                .password("tester1234")
                .build();

        given(userRepository.save(any())).willReturn(user);
    }

    @Test
    void 사용자_생성_서비스() {
        User user = User.builder()
                .id(1L)
                .name("TESTER")
                .email("tester@test.com")
                .password("tester1234")
                .build();

        User createdUser = userService.createUser(user);

        assertThat(createdUser.getId()).isEqualTo(1L);
        assertThat(createdUser.getName()).isEqualTo("TESTER");
        assertThat(createdUser.getEmail()).isEqualTo("tester@test.com");

        verify(userRepository).save(any());
    }

    @Test
    void 사용자_생성시_이메일_중복될떄() {
        User user = User.builder()
                .id(1L)
                .name("TESTER")
                .email("tester@test.com")
                .password("tester1234")
                .build();

        given(userRepository.findByEmail(any())).willReturn(Optional.of(user));

        assertThatThrownBy(() -> {
            userService.createUser(user);
        }).isInstanceOf(EmailExistedException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    void 유효한_데이터로_사용자_인증_서비스() {
        String email = "tester@test.com";
        String password = "tester1234";

        User mockUser = User.builder().email(email).password(password).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);

        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(passwordEncoder.matches(password, user.getPassword())).isEqualTo(true);
    }

    @Test
    void 이메일_오류_사용자_인증_서비스() {
        String email = "wrong_email@test.com";
        String password = "tester1234";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            userService.authenticate(email, password);
        }).isInstanceOf(EmailDoesNotExistedException.class);
    }

    @Test
    void 비밀번호_오류_사용자_인증_서비스() {
        String email = "tester@test.com";
        String password = "x";

        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        given(passwordEncoder.matches(any(), any())).willReturn(false);

        assertThatThrownBy(() -> {
            userService.authenticate(email, password);
        }).isInstanceOf(WrongPasswordException.class);
    }
}

