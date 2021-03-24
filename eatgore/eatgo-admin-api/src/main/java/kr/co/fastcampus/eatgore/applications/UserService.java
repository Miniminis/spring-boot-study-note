package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.User;
import kr.co.fastcampus.eatgore.domains.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserDetail(Long id) {
        User user = userRepository.findById(id).orElseGet(null);

        if (!user.isActivate()) {
            return null;
        }

        return user;
    }

    public User updateUser(Long id, User user) {
        User selectedUser = userRepository.findById(id).orElse(null);

        selectedUser.setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());

        return userRepository.save(selectedUser);
    }

    public User deactivateUser(Long id) {
        User selectedUser = userRepository.findById(id).orElseGet(null);
        selectedUser.deActivate();
        return selectedUser;
    }
}
