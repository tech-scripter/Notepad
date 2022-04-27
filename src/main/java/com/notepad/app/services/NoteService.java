package com.notepad.app.services;

import com.notepad.app.payloads.ModifiedNote;
import com.notepad.app.models.Note;
import com.notepad.app.models.User;
import com.notepad.app.repositories.NoteRepository;
import com.notepad.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(UserRepository userRepository,
                       NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    public ResponseEntity<?> display() {
        Optional<User> user = getCurrentUser();

        if (user.isPresent()) {
            List<Note> userNotes = noteRepository.findAll();
            return new ResponseEntity<>(userNotes, HttpStatus.OK);
        }

        return new ResponseEntity<>("Время сессии истекло! Пожалуйста, войдите снова.",
                HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> create(Note note) {
        Optional<User> user = getCurrentUser();

        if (user.isPresent()) {
            noteRepository.save(new Note(note.getTitle(),
                    note.getContent(),
                    LocalDateTime.now(),
                    user.get()));
            return new ResponseEntity<>("Заметка создана!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Время сессии истекло! Пожалуйста, войдите снова.",
                HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> delete(String title) {
        Optional<User> user = getCurrentUser();

        if (user.isPresent()) {
            noteRepository.deleteByTitle(title);
            return new ResponseEntity<>("Заметка удалена!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Время сессии истекло! Пожалуйста, войдите снова.",
                HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> update(String id, ModifiedNote modifiedNote) {
        Optional<User> user = getCurrentUser();

        if (user.isPresent()) {
            noteRepository.updateNoteById(Long.parseLong(id),
                    modifiedNote.getTitle(),
                    modifiedNote.getContent(),
                    LocalDateTime.now(),
                    user.get());
            return new ResponseEntity<>("Заметка изменена!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Время сессии истекло! Пожалуйста, войдите снова.",
                HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> displayAllNotesById(String id) {
        Optional<User> user = getCurrentUser();

        if (user.isPresent()) {
            List<Note> userNotesById = noteRepository.findAllNotesByUserId(Long.parseLong(id));
            return new ResponseEntity<>(userNotesById, HttpStatus.OK);
        }

        return new ResponseEntity<>("Время сессии истекло! Пожалуйста, войдите снова.",
                HttpStatus.UNAUTHORIZED);
    }

    private Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return userRepository.findByUsername(name);
    }
}
