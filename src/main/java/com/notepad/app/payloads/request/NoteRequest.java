package com.notepad.app.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class NoteRequest implements Serializable {
    private final String title;
    private final String content;
}
