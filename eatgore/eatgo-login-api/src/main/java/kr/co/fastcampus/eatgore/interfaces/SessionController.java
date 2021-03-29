package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.UserService;
import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.domains.requests.SessionRequestDto;
import kr.co.fastcampus.eatgore.domains.responses.SessionResponseDto;
import kr.co.fastcampus.eatgore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<SessionResponseDto> createWithSession(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {

        //사용자 인증
        User user = userService.authenticate(resource.getEmail(), resource.getPassword());

        //인증 완료 후 jwt token 발행
        String accessToken = jwtUtil.createToken(user.getId(), user.getName());

        SessionResponseDto sessionResponseDto = SessionResponseDto.builder()
                .accessToken(accessToken)
                .build();

        URI url = new URI("/login");
        return ResponseEntity.created(url).body(sessionResponseDto);
    }
}
