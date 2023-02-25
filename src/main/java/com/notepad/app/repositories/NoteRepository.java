package com.notepad.app.repositories;

import com.notepad.app.models.Note;
import com.notepad.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("select n from Note n " +
            "where n.user = ?1 and (upper(n.title) like upper(concat('%', ?2, '%')) or upper(n.content) like upper(concat('%', ?2, '%')))")
    List<Note> findAllByUserAndTextInTitleOrContent(User user, String text);

    List<Note> findAllByUser(User user);

    Optional<Note> findByUserAndId(User user, Long id);

    boolean existsByUserAndId(User user, Long id);

    void deleteByUserAndId(User user, Long id);
}
