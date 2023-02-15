package com.stinger.app.stinger;

import com.kwabenaberko.newsapilib.NewsApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StingerApplication {
    @Value("${news-api-client.key}")
    private String apiKey;

    @Bean
    public NewsApiClient newsApiClient() {
        return new NewsApiClient(apiKey);
    }

    public static void main(String[] args) {
        SpringApplication.run(StingerApplication.class, args);
    }

}
