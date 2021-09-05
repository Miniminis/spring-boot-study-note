package com.example.restserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto<T> {

    private Header header;

    private T reqBody;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Header {

        private int requestCode;

    }

}
