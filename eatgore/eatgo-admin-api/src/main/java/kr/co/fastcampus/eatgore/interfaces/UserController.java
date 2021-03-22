package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.UserService;
import kr.co.fastcampus.eatgore.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> create(@RequestBody User user) throws URISyntaxException {
        userService.createUser(user);

        URI location = new URI("/user");
        return ResponseEntity.created(location).body("{}");
    }

    @GetMapping("/user/{id}")
    public User detail(@PathVariable("id") Long id) {
        return userService.getUserDetail(id);
    }

    @PatchMapping("/user/{id}")
    public User update(@PathVariable("id") Long id,
                         @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deactivateUser(id);

        return "{}";
    }
}
