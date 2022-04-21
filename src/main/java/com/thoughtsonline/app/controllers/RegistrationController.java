package com.thoughtsonline.app.controllers;

import com.thoughtsonline.app.payloads.RegistrationForm;
import com.thoughtsonline.app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @GetMapping
    public String registerForm() {
        return "signup";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form) {
        return registrationService.register(form);
    }
}
