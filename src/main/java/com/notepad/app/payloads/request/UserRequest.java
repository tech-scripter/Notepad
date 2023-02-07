package com.notepad.app.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserRequest implements Serializable {
    private final String email;
    private final String password;
}
