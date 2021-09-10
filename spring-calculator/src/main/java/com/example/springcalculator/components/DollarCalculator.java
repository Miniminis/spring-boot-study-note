package com.example.springcalculator.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator {

    private int price = 1;

    private final MarketApi marketApi;

    @Override
    public void init() {
        price = marketApi.connect();
    }

    @Override
    public int plus(int x, int y) {
        x = x * price;
        y = y * price;

        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x = x * price;
        y = y * price;

        return x - y;
    }

}
