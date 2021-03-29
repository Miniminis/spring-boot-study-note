package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.UserService;
import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.domains.exceptions.EmailDoesNotExistedException;
import kr.co.fastcampus.eatgore.domains.exceptions.WrongPasswordException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    void 세션체크_유효한_데이터() throws Exception {
        User mockUser = User.builder()
                .email("tester@test.com")
                .password("pass123456789")
                .build();

        given(userService.authenticate(mockUser.getEmail(), mockUser.getPassword()))
                .willReturn(mockUser);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\", \"password\":\"pass123456789\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string("{\"accessToken\":\"pass123456\"}"));

        verify(userService).authenticate("tester@test.com", "pass123456789");
    }

    @Test
    void 세션체크_비밀번호오류_데이터() throws Exception {
        given(userService.authenticate("tester@test.com", "x"))
                .willThrow(WrongPasswordException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\", \"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(any(), any());
    }

    @Test
    void 세션체크_존재하지_않는_이메일() throws Exception {
        given(userService.authenticate("x@test.com", "pass123"))
                .willThrow(EmailDoesNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@test.com\", \"password\":\"pass123\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(any(), any());
    }
}

