package com.notepad.app.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String getAdminHello(@AuthenticationPrincipal Principal principal) {
        return principal.getName() + " hello!";
    }
}
