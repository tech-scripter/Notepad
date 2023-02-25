package com.notepad.app.payloads.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse<T> {
    private Date timestamp;
    private int status;
    private String reasonPhrase;
    private T message;
    private String path;
}
