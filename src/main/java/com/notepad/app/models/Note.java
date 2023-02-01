package com.notepad.app.models;

import lombok.*;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NOTES")
public class Note {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @Column(name = "TITLE",
            nullable = false)
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED_AT",
            insertable = false,
            updatable = false)
    private LocalDateTime createdAt;

    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED",
            insertable = false,
            updatable = false)
    private Date lastModified;
}
