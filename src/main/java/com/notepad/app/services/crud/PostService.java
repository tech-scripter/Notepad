package com.notepad.app.services.crud;

import com.notepad.app.models.Post;
import com.notepad.app.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAllBySubject(String subject) {
        List<Post> posts = postRepository.findAllBySubject(subject);
        posts.stream()
                .filter(post -> post.getSubject().equals("My opinion"))
                .findFirst()
                .ifPresent(post -> log.info("Post: subject: {}, body: {}, comments: {}",
                        post.getSubject(), post.getBody(), post.getComments()));
        return posts;
    }

    public List<Post> findAllByBody(String body) {
        List<Post> posts = postRepository.findAllByBody(body);
        posts.stream()
                .filter(post -> post.getBody().equals("Nice post"))
                .findFirst()
                .ifPresent(post -> log.info("Post: subject: {}, body: {}, comments: {}",
                        post.getSubject(), post.getBody(), post.getComments()));
        return posts;
    }
}
