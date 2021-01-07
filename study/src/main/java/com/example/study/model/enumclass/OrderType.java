package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {

    ALL(0, "일괄", "일괄발송"),
    EACH(1, "개별", "개별발송");

    private final int id;

    private final String title;

    private final String description;
}
