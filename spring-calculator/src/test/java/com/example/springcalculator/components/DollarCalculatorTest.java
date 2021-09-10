package com.example.springcalculator.components;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DollarCalculatorTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private DollarCalculator dollarCalculator;

    @Test
    void 달러계산기_테스트() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
        dollarCalculator.init();

        int plusResult = dollarCalculator.plus(10, 10);
        int minusResult = dollarCalculator.minus(20, 10);

        assertThat(plusResult).isEqualTo(60000);
        assertThat(minusResult).isEqualTo(30000);
    }
}