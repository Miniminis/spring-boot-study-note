package com.example.springcalculator.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {

    private final ICalculator iCalculator;

    public int plus (int x, int y) {
        iCalculator.init();
        return iCalculator.plus(x, y);
    }

    public int minus(int x, int y) {
        iCalculator.init();
        return iCalculator.minus(x, y);
    }

}
