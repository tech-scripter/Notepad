package com.notepad.app.controllers;

import com.notepad.app.models.Post;
import com.notepad.app.services.crud.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/by-subject")
    public ResponseEntity<?> getAllPostsBySubject(@RequestParam String subject) {
        List<Post> posts = postService.findAllBySubject(subject);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/by-body")
    public ResponseEntity<?> getAllPostsByBody(@RequestParam String body) {
        List<Post> posts = postService.findAllByBody(body);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
