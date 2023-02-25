package com.notepad.app.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage implements Serializable {
    private String name;
}
