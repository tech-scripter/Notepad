package com.notepad.app.services;

import com.notepad.app.configs.ApplicationConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@AllArgsConstructor
public class AppService {
    private final ApplicationConfig applicationConfig;

    @PostConstruct
    public void readConfigs() {
        log.info("Reading configuration {} - {}", applicationConfig.getToken(), applicationConfig.getUsername());
    }
}
