package com.notepad.app.models;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private String body;

    @ManyToOne
    private Post post;
}
