package com.notepad.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifiedNote {
    private String title;
    private String content;
}
