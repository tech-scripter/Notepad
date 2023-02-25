package com.notepad.app.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse implements Serializable {
    private String hash;
    private Instant givenAt;
    private Instant expiresAt;
}
