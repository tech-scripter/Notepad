package com.notepad.app.controllers;

import com.notepad.app.exceptions.NoteNotFoundException;
import com.notepad.app.exceptions.RequestValidationException;
import com.notepad.app.exceptions.UserAlreadyExistsException;
import com.notepad.app.payloads.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleException(NoteNotFoundException e,
                                                            HttpServletRequest request) {
        log.info("{} handled", e.getClass().getName());
        ErrorResponse<?> response = getErrorResponse(request, NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ErrorResponse<?>> handleException(RequestValidationException e,
                                             HttpServletRequest request) {
        log.info("{} handled", e.getClass().getName());
        List<String> messages = new ArrayList<>(List.of(e.getMessage().split("\\.")));
        ErrorResponse<?> response = getErrorResponse(request, BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(), messages);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse<?>> handleException(UserAlreadyExistsException e,
                                             HttpServletRequest request) {
        log.info("{} handled", e.getClass().getName());
        ErrorResponse<?> response = getErrorResponse(request, BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    private ErrorResponse<?> getErrorResponse(HttpServletRequest request, int status,
                                           String reasonPhrase, String message) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .status(status)
                .reasonPhrase(reasonPhrase)
                .message(message)
                .path(request.getRequestURI())
                .build();
    }

    private ErrorResponse<?> getErrorResponse(HttpServletRequest request, int status,
                                              String reasonPhrase, List<String> messages) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .status(status)
                .reasonPhrase(reasonPhrase)
                .message(messages)
                .path(request.getRequestURI())
                .build();
    }
}
