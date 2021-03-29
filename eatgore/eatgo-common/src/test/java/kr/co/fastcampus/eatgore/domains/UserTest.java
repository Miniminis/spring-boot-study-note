package kr.co.fastcampus.eatgore.domains;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class UserTest {

    @Test
    void 비밀번호_존재할떄_authToken_접근() {
        User user = User.builder()
                .email("tester@test.com")
                .password("tester1234566663")
                .level(1L)
                .build();

        assertThat(user.getAuthToken()).isEqualTo("tester1234");
    }

    @Test
    void 비밀번호_존재하지않을떄_authToken_접근() {
        User user = User.builder()
                .email("tester@test.com")
                .level(1L)
                .build();

        assertThat(user.getAuthToken()).isEqualTo(null);
    }

}