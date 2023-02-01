package com.notepad.app.services;

import com.notepad.app.exceptions.NoteNotFoundException;
import com.notepad.app.models.Note;
import com.notepad.app.repositories.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final ModelMapper mapper;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(ModelMapper mapper,
                       NoteRepository noteRepository) {
        this.mapper = mapper;
        this.noteRepository = noteRepository;
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

//    public List<Note> findAllNotesByTextContainingInTitleOrContent(String text) {
//        return noteRepository.findAllByTextContainingInTitleOrContent(text);
//    }

    public Note findNoteById(Long id) {
        String message = String.format("Note with id %d not found", id);
        return noteRepository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException(message));
    }

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

//    public Note findNoteByIdAndUser(Long id, User user) {
//        String msg = String.format("Note with id %d and user id %d not found",
//                id, user.getId());
//        return noteRepository
//                .findByIdAndUser(id, user)
//                .orElseThrow(() -> new NoteNotFoundException(msg));
//    }

//    public List<Note> findAllNotesByUser(User user) {
//        return noteRepository.findAllByUser(user);
//    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
