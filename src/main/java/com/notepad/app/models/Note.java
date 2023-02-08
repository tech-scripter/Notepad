package com.notepad.app.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
            updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "LAST_MODIFIED", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastModified;

    @ManyToOne
    private User user;
}
