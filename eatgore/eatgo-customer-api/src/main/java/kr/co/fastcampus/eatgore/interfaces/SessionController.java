package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.UserService;
import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.domains.requests.SessionRequestDto;
import kr.co.fastcampus.eatgore.domains.responses.SessionResponseDto;
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

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> createWithSession(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {
        User user = userService.authenticate(resource.getEmail(), resource.getPassword());

        SessionResponseDto sessionResponseDto = SessionResponseDto.builder()
                .accessToken(user.getAuthToken())
                .build();

        URI url = new URI("/session");
        return ResponseEntity.created(url).body(sessionResponseDto);
    }
}
