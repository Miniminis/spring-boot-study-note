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
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(200)
                .message("Succeed")
                .build();
    }

    //response 200 with data
    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(200)
                .message("Succeed")
                .data(data)
                .build();
    }

    //response 500 error with msg
    public static <T> Header<T> ERROR(String msg) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(500)
                .message(msg)
                .build();
    }

}
