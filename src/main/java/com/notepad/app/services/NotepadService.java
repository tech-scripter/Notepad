package com.notepad.app.services;

import com.notepad.app.models.Note;
import com.notepad.app.payloads.request.NoteRequest;
import com.notepad.app.payloads.response.NoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotepadService {
    private final ModelMapper mapper;
    private final NoteService noteService;

    @Autowired
    public NotepadService(ModelMapper mapper, NoteService noteService) {
        this.mapper = mapper;
        this.noteService = noteService;
    }

    public ResponseEntity<List<NoteResponse>> processNoteSaving(NoteRequest noteRequest) {
        log.debug("Object from client {}", noteRequest);
        Note note = mapper.map(noteRequest, Note.class);
        note.setCreatedAt(LocalDateTime.now());
        noteService.saveNote(note);
        List<NoteResponse> notes = getAllNoteResponse();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    public ResponseEntity<?> processNoteUpdating(Long id, NoteRequest noteRequest) {
        Note note = noteService.findNoteById(id);
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        noteService.saveNote(note);
        List<NoteResponse> noteResponses = getAllNoteResponse();
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    private List<NoteResponse> getAllNoteResponse() {
        return noteService
                .findAllNotes()
                .stream()
                .map(e -> mapper.map(e, NoteResponse.class))
                .collect(Collectors.toList());
    }
}
