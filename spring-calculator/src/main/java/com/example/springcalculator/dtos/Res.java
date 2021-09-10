package com.example.springcalculator.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Res {

    private int calculateResult;

    private Body responseBody;

    @Data
    @Builder
    @NoArgsConstructor
    public static class Body {
        private final String resultCode = "OK";
    }

}
