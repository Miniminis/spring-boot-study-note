package com.example.zaliving.controllers;

import com.example.zaliving.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void 회원가입_컨트롤러() throws Exception {
        mvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tester\", \"email\":\"tester@test.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{}")));

        verify(userService).joinUser(any());
    }
}

