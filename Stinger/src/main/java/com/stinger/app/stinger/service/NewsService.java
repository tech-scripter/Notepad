package com.stinger.app.stinger.service;

import com.stinger.app.stinger.exceptions.SourcesNotFoundException;
import com.stinger.app.stinger.models.SourcesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NewsService {
    @Value("${news-api-client.key}")
    private String apiKey;
    @Value("${news-api-client.sources.request.parameter.category}")
    private String category;
    @Value("${news-api-client.sources.request.parameter.language}")
    private String language;
    @Value("${news-api-client.sources.request.parameter.country}")
    private String country;



    private final RestTemplate restTemplate;

    @Autowired
    public NewsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public SourcesResponse getSourcesResponse() {
        String url = String.format("https://newsapi.org/v2/top-headlines/sources?apiKey=%s", apiKey);
        SourcesResponse response = restTemplate.getForObject(url, SourcesResponse.class);

        if (response == null) {
            throw new SourcesNotFoundException("Sources not found");
        }

        return response;
    }
}
