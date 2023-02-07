package com.notepad.app.controllers;

import com.notepad.app.payloads.request.UserRequest;
import com.notepad.app.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) {
        return authenticationService.signUp(userRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserRequest userRequest) {
        return authenticationService.signIn(userRequest);
    }
}
