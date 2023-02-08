package com.notepad.app.controllers;

import com.notepad.app.exceptions.RequestValidationException;
import com.notepad.app.payloads.request.UserRequest;
import com.notepad.app.services.AuthenticationService;
import com.notepad.app.utils.ValidationErrorGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserRequest userRequest,
                                    BindingResult bindingResult) {
        checkValidationRequestErrors(bindingResult);
        return authenticationService.signUp(userRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody UserRequest userRequest,
                                    BindingResult bindingResult) {
        checkValidationRequestErrors(bindingResult);
        return authenticationService.signIn(userRequest);
    }

    private void checkValidationRequestErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = ValidationErrorGenerator.getMessage(bindingResult);
            throw new RequestValidationException(msg);
        }
    }
}
