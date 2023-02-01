package com.notepad.app.repositories;

import com.notepad.app.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
//    @Query(value = "select Note from Note n where n.title like ?1 or n.content like ?1")
//    List<Note> findAllByTextContainingInTitleOrContent(String text);

//    Optional<Note> findByIdAndUser(Long id, User user);
//    List<Note> findAllByUser(User user);
}
