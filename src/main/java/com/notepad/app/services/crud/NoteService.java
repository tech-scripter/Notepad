package com.notepad.app.services.crud;

import com.notepad.app.exceptions.NoteNotFoundException;
import com.notepad.app.models.Note;
import com.notepad.app.models.User;
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

    public List<Note> findAllNotesByUserAndText(User user, String text) {
        log.info("Notes have been found by text ({})", text);
        return noteRepository.findAllByUserAndTextInTitleOrContent(user, text);
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

    public Note findNoteByUserAndId(User user, Long id) {
        return noteRepository
                .findByUserAndId(user, id)
                .orElseThrow(() -> {
                    RuntimeException e = new NoteNotFoundException(getMsgNoteNotFoundBy(user, id));
                    log.error("Exception has been thrown: {}", e.getMessage());
                    return e;
                });
    }

    public List<Note> findAllNotes() {
        log.info("Notes have been found");
        return noteRepository.findAll();
    }

    public List<Note> findAllNotesByUser(User user) {
        log.info("Notes by user id ({}) have been found", user.getId());
        return noteRepository.findAllByUser(user);
    }

    public void deleteNoteById(Long id) {
        log.debug("Deleting note with id ({})", id);
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            RuntimeException e = new NoteNotFoundException(getMsgNoteNotFoundBy(id));
            log.error("Exception has been thrown: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteNoteByUserAndId(User user, Long id) {
        log.debug("Deleting note with id ({}) of a user with name ({})",
                id, user.getUsername());
        if (noteRepository.existsByUserAndId(user, id)) {
            noteRepository.deleteByUserAndId(user, id);
        } else {
            RuntimeException e = new NoteNotFoundException(getMsgNoteNotFoundBy(user, id));
            log.error("Exception has been thrown: {}", e.getMessage());
            throw e;
        }
    }

    private String getMsgNoteNotFoundBy(Long id) {
        return String.format("Note with id %d not found", id);
    }

    private String getMsgNoteNotFoundBy(User user, Long id) {
        return String.format("Note with id (%d) and user id (%d) not found", id, user.getId());
    }
}
