package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.domains.exceptions.EmailDoesNotExistedException;
import kr.co.fastcampus.eatgore.domains.exceptions.EmailExistedException;
import kr.co.fastcampus.eatgore.domains.exceptions.WrongPasswordException;
import kr.co.fastcampus.eatgore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailDoesNotExistedException(email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException();
        }

        return user;
    }
}
