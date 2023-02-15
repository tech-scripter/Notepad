package com.notepad.app.services;

import com.stinger.app.stinger.models.Source;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "news", groupId = "group_id")
    public Source consume(Source source) {
        return source;
    }
}
