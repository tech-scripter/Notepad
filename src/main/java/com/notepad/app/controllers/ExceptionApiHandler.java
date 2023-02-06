package com.notepad.app.controllers;

import com.notepad.app.exceptions.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NoteNotFoundException e) {
        log.info("{} handled", e.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(e.getMessage(), e.getCause()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleMessage(ConstraintViolationException e) {
        log.info("{} handled", e.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage(), e.getCause()));
    }
}
