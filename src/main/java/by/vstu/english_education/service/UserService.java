package by.vstu.english_education.service;

import by.vstu.english_education.entity.Dto.UserProfileDTO;
import by.vstu.english_education.entity.User;
import by.vstu.english_education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return user;
    }

    public void updateUserProfile(String username, UserProfileDTO userProfileDTO) {
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            user.setFirstname(userProfileDTO.getFirstname());
            user.setLastname(userProfileDTO.getLastname());
            user.setPatronymic(userProfileDTO.getPatronymic());
            userRepository.save(user);
        }
    }

    public void changePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username).get();
        newPassword = passwordEncoder.encode(newPassword);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_STUDENT");
        userRepository.save(user);
    }
}
