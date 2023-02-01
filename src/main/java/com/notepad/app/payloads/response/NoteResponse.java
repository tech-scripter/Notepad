package com.notepad.app.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse implements Serializable {
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
