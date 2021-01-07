package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {

    UNREGISTERED(2, "미등록", "상품 미등록상태"),
    WAITING(1, "검수중", "상품 검수 진행중"),
    REGISTERED(0, "등록", "상품 등록상태");

    private final int id;

    private final String status;

    private final String description;

}
