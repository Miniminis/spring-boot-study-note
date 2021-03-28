package kr.co.fastcampus.eatgore.domains.requests;

import lombok.Data;

@Data
public class SessionRequestDto {

    private String email;

    private String password;

}
