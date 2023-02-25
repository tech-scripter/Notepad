package com.stinger.app.stinger.models;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SourcesResponse implements Serializable {
    private String status;
    private List<Source> sources;
}
