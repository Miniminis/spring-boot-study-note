package com.example.zaliving.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDtoLogin {

    private String authToken;

}
