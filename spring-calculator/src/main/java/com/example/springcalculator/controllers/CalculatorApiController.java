package com.example.springcalculator.controllers;

import com.example.springcalculator.components.Calculator;
import com.example.springcalculator.dtos.Req;
import com.example.springcalculator.dtos.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorApiController {

    private final Calculator calculator;

    @GetMapping("/plus")
    public int plus(@RequestParam int x, @RequestParam int y) {
        return calculator.plus(x, y);
    }


    @PostMapping("/minus")
    public Res minus(@RequestBody Req req) {
        int result = calculator.minus(req.getX(), req.getY());

        return Res.builder()
                .calculateResult(result)
                .responseBody(Res.Body.builder().build())
                .build();
    }
}
