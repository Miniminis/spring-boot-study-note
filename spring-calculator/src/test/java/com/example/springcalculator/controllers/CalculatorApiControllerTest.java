package com.example.springcalculator.controllers;

import com.example.springcalculator.components.Calculator;
import com.example.springcalculator.components.DollarCalculator;
import com.example.springcalculator.components.MarketApi;
import com.example.springcalculator.dtos.Req;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(CalculatorApiController.class)
@AutoConfigureMockMvc
@Import({Calculator.class, DollarCalculator.class})
class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void init() {
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }


    @Test
    void 더하기_계산기_컨트롤러_테스트_GET() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/plus")
                .queryParam("x", "10")
                .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 빼기_계산기_컨트롤러_테스트_POST() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new Req(20, 10));

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.calculateResult").value("30000")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.responseBody.resultCode").value("OK")
        ).andDo(
                MockMvcResultHandlers.print()
        );

    }
}