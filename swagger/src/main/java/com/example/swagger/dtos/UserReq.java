package com.example.swagger.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    @ApiModelProperty(value = "이름", example = "tom", required = true)
    private String name;

    @ApiModelProperty(value = "나이", example = "10", required = true)
    private int age;

}
