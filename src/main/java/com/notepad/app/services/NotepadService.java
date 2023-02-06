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
        Note note = mapper.map(noteRequest, Note.class);
        log.debug("({}) has been mapped to the ({})", noteRequest, note);
        noteService.saveNote(note);
        log.debug("({}) is being saved", note);
        List<NoteResponse> notes =
                mapToListOfNoteResponse(noteService.findAllNotes());
        log.debug("A list of note responses has been found");
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> processNoteUpdating(Long id, NoteRequest noteRequest) {
        Note note = noteService.findNoteById(id);
        log.debug("A note with id ({}) has been found", id);
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        log.debug("Note fields have been updated");
        noteService.saveNote(note);
        log.debug("({}) has been saved", note);
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotes());
        log.debug("A list of note responses has been found");
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> processNoteDeletion(Long id) {
        noteService.deleteNoteById(id);
        log.debug("Note with id ({}) has been deleted", id);
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotes());
        log.debug("A list of note responses has been found");
        return new ResponseEntity<>(noteResponses, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<NoteResponse>> processNotesReceiving(String text) {
        List<NoteResponse> noteResponses =
                mapToListOfNoteResponse(noteService.findAllNotesByText(text));
        log.debug("A list of note responses has been found");
        return new ResponseEntity<>(noteResponses, HttpStatus.OK);
    }

    private List<NoteResponse> mapToListOfNoteResponse(List<Note> notes) {
        return notes
                .stream()
                .map(note -> mapper.map(note, NoteResponse.class))
                .collect(Collectors.toList());
    }
}
