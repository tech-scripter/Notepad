package com.stinger.app.stinger.models;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Source implements Serializable {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) && Objects.equals(name, source.name) && Objects.equals(description, source.description) && Objects.equals(url, source.url) && Objects.equals(category, source.category) && Objects.equals(language, source.language) && Objects.equals(country, source.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, url, category, language, country);
    }
}
