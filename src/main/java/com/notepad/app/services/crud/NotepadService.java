package com.notepad.app.services.crud;

import com.notepad.app.models.Note;
import com.notepad.app.models.User;
import com.notepad.app.payloads.request.NoteRequest;
import com.notepad.app.payloads.response.NoteResponse;
import com.notepad.app.services.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotepadService {
    private final ModelMapper mapper;
    private final NoteService noteService;
    private final UserService userService;

    @Transactional
    public ResponseEntity<List<NoteResponse>> processNoteSaving(Principal principal,
                                                                NoteRequest noteRequest) {
        log.debug("Saving note with request body ({}) to user with name ({})",
                principal.getName(), noteRequest);

        User user = userService.findByEmail(principal.getName());
        Note note = mapper.map(noteRequest, Note.class);
        note.setUser(user);
        note = noteService.saveNote(note);
        List<NoteResponse> notes =
                mapToListOfNoteResponse(noteService.findAllNotesByUser(note.getUser()));
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> processNoteUpdating(Principal principal,
                                                 Long id, NoteRequest noteRequest) {
        log.debug("Updating note with id ({}) and request body ({})" +
                "of a user with name ({})", id, noteRequest, principal.getName());

        User user = userService.findByEmail(principal.getName());
        Note note = noteService.findNoteByUserAndId(user, id);
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        noteService.saveNote(note);
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotesByUser(user));
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> processNoteDeletion(Principal principal, Long id) {
        log.debug("Deleting note with id ({}) of a user with name ({})",
                id, principal.getName());

        User user = userService.findByEmail(principal.getName());
        noteService.deleteNoteByUserAndId(user, id);
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotesByUser(user));
        return new ResponseEntity<>(noteResponses, HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<List<NoteResponse>> processNotesReceiving(String text,
                                                                    Principal principal) {
        User user = userService.findByEmail(principal.getName());
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotesByUserAndText(user, text));
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    private List<NoteResponse> mapToListOfNoteResponse(List<Note> notes) {
        return notes
                .stream()
                .map(note -> mapper.map(note, NoteResponse.class))
                .collect(Collectors.toList());
    }
}
