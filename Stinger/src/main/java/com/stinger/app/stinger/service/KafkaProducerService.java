package com.stinger.app.stinger.service;

import com.stinger.app.stinger.models.Source;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {
    private final MyService myService;
    private final KafkaTemplate<String, Source> kafkaTemplate;

    public KafkaProducerService(MyService myService, KafkaTemplate<String, Source> kafkaTemplate) {
        this.myService = myService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 60000)
    public void sendMessage() {
        Source source = myService.getSource();
        kafkaTemplate.send("news", source);
    }
}
