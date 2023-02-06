package com.notepad.app.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class NoteRequest implements Serializable {
    @NotNull
    @NotEmpty
    @NotBlank
    private final String title;

    @NotEmpty
    @NotBlank
    @NotNull
    private final String content;
}
