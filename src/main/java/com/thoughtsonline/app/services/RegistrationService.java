package com.thoughtsonline.app.services;

import com.thoughtsonline.app.payloads.RegistrationForm;
import com.thoughtsonline.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegistrationForm form) {

        if (userRepository.existsByEmail(form.getEmail())) {
            return "redirect:/registration";
        }

        userRepository.save(form.convertToUserEntity(passwordEncoder));


        return "redirect:/login";
    }
}
