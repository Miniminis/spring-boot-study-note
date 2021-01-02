package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    private LocalDateTime transactionTime;

    private int status;

    private String message;

    private T data;

    //response 200

    //response 200 with data

    //response 500 error with msg

}
