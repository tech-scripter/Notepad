package com.stinger.app.stinger.service;

import com.stinger.app.stinger.exceptions.SourceNotFoundException;
import com.stinger.app.stinger.models.Source;
import com.stinger.app.stinger.models.SourcesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MyService {
    private final NewsService newsService;

    @Autowired
    public MyService(NewsService newsService) {
        this.newsService = newsService;
    }

    public Source getSource() {
        long start = System.currentTimeMillis();
        Source source = null;
        try {
            SourcesResponse sourcesResponse = newsService.getSourcesResponse();
            List<Source> sources = sourcesResponse.getSources();
            source = sources.stream()
                    .skip((int) (sources.size() * Math.random()))
                    .findAny()
                    .orElseThrow(() -> new SourceNotFoundException("Source not found"));
        } catch (SourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        long end = System.currentTimeMillis() - start;
        log.debug("Time spent on method: {} ms", end);
        return source;
    }



}
