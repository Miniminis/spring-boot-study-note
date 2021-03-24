package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.UserService;
import kr.co.fastcampus.eatgore.domains.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void initMockData() {
        //조회성 데이터 - 리스트, 상세
        User user = User.builder()
                .id(1L)
                .name("TESTER")
                .email("tester@test.com")
                .password("tester1234")
                .level(1L)
                .build();

        given(userService.getUserDetail(1L)).willReturn(user);
    }

    @Test
    void 사용자_생성_컨트롤러() throws Exception {
        given(userService.createUser(any()))
                .will(invocation -> {
                   User user = invocation.getArgument(0);
                   return User.builder()
                           .id(1L)
                           .name(user.getName())
                           .email(user.getEmail())
                           .password(user.getPassword())
                           .build();
                });

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\", \"name\":\"TESTER\", \"password\":\"tester1234\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{}")));

        verify(userService).createUser(any());
    }

    @Test
    void 사용자_조회_컨트롤러() throws Exception {
        mvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"TESTER\"")))
                .andExpect(content().string(containsString("\"email\":\"tester@test.com\"")));

        verify(userService).getUserDetail(any());
    }

    @Test
    void 사용자_수정_컨트롤() throws Exception {
        mvc.perform(patch("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.co.kr\", \"name\":\"HUMAN\", \"password\":\"tester5678\"}"))
                .andExpect(status().isOk());

        verify(userService).updateUser(any(), any());
    }

    @Test
    void 사용자_삭제_컨트롤() throws Exception {
        mvc.perform(delete("/user/1"))
                .andExpect(status().isOk());

        verify(userService).deactivateUser(1L);
    }
}