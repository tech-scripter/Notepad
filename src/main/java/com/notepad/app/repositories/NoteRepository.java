package com.notepad.app.repositories;

import com.notepad.app.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("select n from Note n " +
            "where upper(n.title) like upper(concat('%', ?1, '%')) or upper(n.content) like upper(concat('%', ?1, '%'))")
    List<Note> findAllByTextInTitleOrContent(String text);
}
