package com.notepad.app.controllers;

import com.notepad.app.payloads.request.NoteRequest;
import com.notepad.app.payloads.response.NoteResponse;
import com.notepad.app.services.NotepadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@EnableCaching
@RestController
@RequestMapping("/api/notepad")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class NoteController {
    private final NotepadService notepadService;

    @Autowired
    public NoteController(NotepadService notepadService) {
        this.notepadService = notepadService;
    }

    @GetMapping("/notes")
    public ResponseEntity<?> getAllNotesByText(@RequestParam String text) {
        log.debug("Delegating the request parameter ({}) to the ({}) layer",
                text, notepadService.getClass().getSimpleName());
        return notepadService.processNotesReceiving(text);
    }

    @PostMapping("/notes")
    public ResponseEntity<List<NoteResponse>> createNote(@Valid @RequestBody NoteRequest noteRequest) {
        // TODO: set up validation
        log.debug("Delegating the request body ({}) to the ({}) layer",
                noteRequest, notepadService.getClass().getSimpleName());
        return notepadService.processNoteSaving(noteRequest);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id,
                                        @RequestBody NoteRequest noteRequest) {
        log.debug("Delegating the id ({}) along with the request body ({}) to the ({})",
                id, noteRequest, notepadService.getClass().getSimpleName());
        return notepadService.processNoteUpdating(id, noteRequest);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        log.debug("Delegating the id ({}) to the ({}) layer",
                id, notepadService.getClass().getSimpleName());
        return notepadService.processNoteDeletion(id);
    }
}
