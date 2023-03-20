package com.notepad.app.services.auth;

import com.notepad.app.exceptions.UserNotFoundException;
import com.notepad.app.models.User;
import com.notepad.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    String msg = String.format("User with email %s not found", email);
                    return new UserNotFoundException(msg);
                });
    }
}
