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
    @NotNull(message = "Title should not be null")
    private final String title;

    @NotEmpty(message = "Content should not be empty")
    @NotBlank(message = "Content should not be blank")
    @NotNull(message = "Content should not be null")
    private final String content;
}
