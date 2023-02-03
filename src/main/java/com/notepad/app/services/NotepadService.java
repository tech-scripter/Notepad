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

import javax.transaction.Transactional;
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

    @Transactional
    public ResponseEntity<List<NoteResponse>> processNoteSaving(NoteRequest noteRequest) {
        log.debug("Object from client {}", noteRequest);
        Note note = mapper.map(noteRequest, Note.class);
        noteService.saveNote(note);
        List<NoteResponse> notes =
                mapToListOfNoteResponse(noteService.findAllNotes());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> processNoteUpdating(Long id, NoteRequest noteRequest) {
        Note note = noteService.findNoteById(id);
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        noteService.saveNote(note);
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotes());
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> processNoteDeletion(Long id) {
        noteService.deleteNoteById(id);
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotes());
        return new ResponseEntity<>(noteResponses, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<NoteResponse>> processNotesReceiving() {
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotes());
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    public ResponseEntity<List<NoteResponse>> processNotesReceiving(String text) {
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotesByText(text));
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    private List<NoteResponse> mapToListOfNoteResponse(List<Note> notes) {
        return notes
                .stream()
                .map(note -> mapper.map(note, NoteResponse.class))
                .collect(Collectors.toList());
    }
}
