package com.notepad.app.services;

import com.notepad.app.exceptions.NoteNotFoundException;
import com.notepad.app.models.Note;
import com.notepad.app.repositories.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note saveNote(Note note) {
        log.info("Note {} saved", note);
        return noteRepository.save(note);
    }

    public List<Note> findAllNotesByText(String text) {
        log.info("Notes have been found by text ({})", text);
        return noteRepository.findAllByTextInTitleOrContent(text);
    }

    public Note findNoteById(Long id) {
        return noteRepository
                .findById(id)
                .orElseThrow(() -> {
                    RuntimeException e = new NoteNotFoundException(getMsgNoteNotFoundBy(id));
                    log.error("Exception has been thrown: {}", e.getMessage());
                    return e;
                });
    }

    public List<Note> findAllNotes() {
        log.info("Notes have been found");
        return noteRepository.findAll();
    }

    public void deleteNoteById(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            log.info("Note with id {} has been deleted", id);
        } else {
            RuntimeException e = new NoteNotFoundException(getMsgNoteNotFoundBy(id));
            log.error("Exception has been thrown: {}", e.getMessage());
            throw e;
        }
    }

    private String getMsgNoteNotFoundBy(Long id) {
        return String.format("Note with id %d not found", id);
    }
}
