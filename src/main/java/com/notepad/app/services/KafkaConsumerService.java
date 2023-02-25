package com.notepad.app.services;

import com.stinger.app.stinger.models.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService implements BrokerConsumerService<Source> {
    private final SimpMessagingTemplate template;

    @Autowired
    public KafkaConsumerService(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    @KafkaListener(topics = "news", groupId = "group_id")
    public void consume(@Payload Source source) {
        System.out.println("Consumed: " + source);
        template.convertAndSend("/topic/greetings", source);
    }
}
