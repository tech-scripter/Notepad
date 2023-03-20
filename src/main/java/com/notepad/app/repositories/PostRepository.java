package com.notepad.app.repositories;

import com.notepad.app.models.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Подтянет только посты без комментариев. Проблема N+1 решена
    @EntityGraph(value = "post-only-entity-graph")
    List<Post> findAllBySubject(String subject);

    // Подтянет посты с комментариями
    @EntityGraph(value = "post-comments-entity-graph")
    List<Post> findAllByBody(String body);

}
