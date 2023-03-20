package com.notepad.app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "POST")
@NoArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "post-only-entity-graph"),
        @NamedEntityGraph(name = "post-comments-entity-graph",
                attributeNodes = {@NamedAttributeNode("comments")})
})
public class Post {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private String subject;
    private String body;

    // Если пометить как EAGER, то граф будет проигнорирован
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
}
