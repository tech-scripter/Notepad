package com.stinger.app.stinger.service;

import com.stinger.app.stinger.exceptions.SourcesNotFoundException;
import com.stinger.app.stinger.models.Source;
import com.stinger.app.stinger.models.SourcesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyService {
    private final NewsService newsService;

    @Autowired
    public MyService(NewsService newsService) {
        this.newsService = newsService;
    }

    public Source getSource() {
        Source source = null;
        try {
            SourcesResponse sourcesResponse = newsService.getSourcesResponse();
            source = sourcesResponse.getSources()
                        .stream()
                        .findAny()
                        .orElseThrow(() -> new SourcesNotFoundException("Source not found"));
        } catch (SourcesNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return source;
    }
}
