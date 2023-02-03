package com.notepad.app.services;

import com.notepad.app.exceptions.NoteNotFoundException;
import com.notepad.app.models.Note;
import com.notepad.app.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> findAllNotesByText(String text) {
        return noteRepository.findAllByTextInTitleOrContent(text);
    }

    public Note findNoteById(Long id) {
        return noteRepository
                .findById(id)
                .orElseThrow(() -> {
                    String message = getMsgNoteNotFoundBy(id);
                    return new NoteNotFoundException(message);
                });
    }

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public void deleteNoteById(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new NoteNotFoundException(getMsgNoteNotFoundBy(id));
        }
    }

    private String getMsgNoteNotFoundBy(Long id) {
        return String.format("Note with id %d not found", id);
    }
}
