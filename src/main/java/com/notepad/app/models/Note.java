package com.notepad.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title",
            nullable = false)
    private String title;

    @Column(name = "content",
            nullable = false)
    private String content;

    @Column(name = "created_at",
            nullable = false)
    @JsonIgnore
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false,
                name = "user_id")
    private User user;

    public Note() {

    }

    public Note(String title, String content, LocalDateTime createdAt, User user) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }
}
