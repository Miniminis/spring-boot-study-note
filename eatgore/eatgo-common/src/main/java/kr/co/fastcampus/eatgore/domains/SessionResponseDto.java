package kr.co.fastcampus.eatgore.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {

    private String accessToken;

}
