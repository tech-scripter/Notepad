package com.notepad.app.repositories;

import com.notepad.app.models.Note;
import com.notepad.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Note WHERE title = ?1")
    void deleteByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Note n " +
    "SET n.title = ?2, n.content = ?3, n.createdAt = ?4 " +
    "WHERE n.id = ?1")
    void updateNoteById(Long id, String title, String content, LocalDateTime time, User user);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM notes WHERE user_id = ?1",
            nativeQuery = true)
    List<Note> findAllNotesByUserId(Long id);
}
