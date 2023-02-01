package com.notepad.app.controllers;

import com.notepad.app.payloads.request.NoteRequest;
import com.notepad.app.payloads.response.NoteResponse;
import com.notepad.app.services.NotepadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notepad")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class NoteController {
    private final NotepadService notepadService;

    @Autowired
    public NoteController(NotepadService notepadService) {
        this.notepadService = notepadService;
    }

    @PostMapping("/notes")
    public ResponseEntity<List<NoteResponse>> createNote(@RequestBody NoteRequest noteRequest) {
        return notepadService.processNoteSaving(noteRequest);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id,
                                        @RequestBody NoteRequest noteRequest) {
        return notepadService.processNoteUpdating(id, noteRequest);
    }

}
