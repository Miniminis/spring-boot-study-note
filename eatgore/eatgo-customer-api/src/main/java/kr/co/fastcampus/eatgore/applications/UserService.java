package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.exceptions.EmailExistedException;
import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        Optional<User> selectedUSer = userRepository.findByEmail(user.getEmail());
        if (selectedUSer.isPresent()) {
            throw new EmailExistedException(selectedUSer.get().getEmail());
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User newUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(encodedPassword)
                .level(user.getLevel())
                .build();

        return userRepository.save(newUser);
    }

    public User authenticate(String email, String password) {
        return null;
    }
}
