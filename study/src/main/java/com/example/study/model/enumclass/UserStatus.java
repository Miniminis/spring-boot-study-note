package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED(0, "REGISTERED", "User Registered"),

    UNREGISTERED(1, "UNREGISTERED", "User Unregistered");

    private final Integer id;

    private final String title;

    private final String description;

}
