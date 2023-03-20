package com.notepad.app.controllers;

import com.notepad.app.exceptions.RequestValidationException;
import com.notepad.app.payloads.request.NoteRequest;
import com.notepad.app.payloads.response.NoteResponse;
import com.notepad.app.services.crud.NotepadService;
import com.notepad.app.utils.ValidationErrorGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@EnableCaching
@RestController
@RequestMapping("/api/notepad")
public class NotepadController {
    private final NotepadService notepadService;

    @Autowired
    public NotepadController(NotepadService notepadService) {
        this.notepadService = notepadService;
    }

    @GetMapping("/notes")
    public ResponseEntity<?> getAllNotesByText(@RequestParam String text,
                                               Principal principal) {
        log.debug("Delegating the request parameter ({}) to the ({}) layer",
                text, notepadService.getClass().getSimpleName());
        return notepadService.processNotesReceiving(text, principal);
    }

    @PostMapping("/notes")
    public ResponseEntity<List<NoteResponse>> createNote(@Valid @RequestBody NoteRequest noteRequest,
                                                         BindingResult bindingResult,
                                                         Principal principal) {
        checkValidationRequestErrors(bindingResult);
        log.debug("Delegating the request body ({}) to the ({}) layer",
                noteRequest, notepadService.getClass().getSimpleName());
        return notepadService.processNoteSaving(principal, noteRequest);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id,
                                        @Valid @RequestBody NoteRequest noteRequest,
                                        BindingResult bindingResult,
                                        Principal principal) {
        checkValidationRequestErrors(bindingResult);
        log.debug("Delegating the id ({}) along with the request body ({}) to the ({})",
                id, noteRequest, notepadService.getClass().getSimpleName());
        return notepadService.processNoteUpdating(principal, id, noteRequest);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id, Principal principal) {
        log.debug("Delegating the id ({}) to the ({}) layer",
                id, notepadService.getClass().getSimpleName());
        return notepadService.processNoteDeletion(principal, id);
    }

    private void checkValidationRequestErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = ValidationErrorGenerator.generate(bindingResult);
            throw new RequestValidationException(msg);
        }
    }
}
