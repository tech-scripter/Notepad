package com.notepad.app.controllers;

import com.notepad.app.payloads.ModifiedNote;
import com.notepad.app.services.NoteService;
import com.notepad.app.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/api/editor")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        return noteService.create(note);
    }

    @DeleteMapping("/{title}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteNote(@PathVariable String title) {
        return noteService.delete(title);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateNote(@PathVariable String id, @RequestBody ModifiedNote note) {
        return noteService.update(id, note);
    }

    @GetMapping("/notes/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> displayAllNotesByUserId(@PathVariable String userId) {
        return noteService.displayAllNotesById(userId);
    }
}
