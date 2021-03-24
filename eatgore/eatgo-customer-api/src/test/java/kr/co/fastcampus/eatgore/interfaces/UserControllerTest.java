package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.UserService;
import kr.co.fastcampus.eatgore.domains.User;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void 사용자_회원가입_컨트롤러() throws Exception {
        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\", \"name\":\"TESTER\", \"password\":\"tester1234\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/user"));

        verify(userService).createUser(any());
    }

}