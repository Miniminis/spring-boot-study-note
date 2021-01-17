package com.example.facebook.mycontact.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    HelloController helloController;

    private MockMvc mockMvc;

    @Test
    void helloWorld() {
        System.out.println(helloController.helloWorld());

        assertEquals(helloController.helloWorld(), "Hello, SpringBoot!");
    }

    @Test
    void mockMvcTest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/helloworld"))
                .andDo(MockMvcResultHandlers.print());
    }
}