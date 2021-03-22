package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.domains.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
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
    void 사용자_조회_서비스() {
        User selectedUser = userService.getUserDetail(1L);

        assertThat(selectedUser.getId()).isEqualTo(1L);
        assertThat(selectedUser.getName()).isEqualTo("TESTER");
        assertThat(selectedUser.getEmail()).isEqualTo("tester@test.com");

        verify(userRepository).findById(1L);
    }

    @Test
    void 사용자_수정_서비스() {
        User user = User.builder()
                .id(1L)
                .name("HUMAN")
                .email("tester@test.co.kr")
                .password("tester5678")
                .build();

        User updatedUser = userService.updateUser(1L, user);

        assertThat(updatedUser.getId()).isEqualTo(user.getId());
        assertThat(updatedUser.getName()).isEqualTo(user.getName());
        assertThat(updatedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(updatedUser.getPassword()).isEqualTo(user.getPassword());

        verify(userRepository).findById(any());
        verify(userRepository).save(any());
    }

    @Test
    void 사용자_삭제_서비스() {
        User user = userService.deactivateUser(1L);

        assertThat(user.getLevel()).isEqualTo(0);
    }
}

