package com.notepad.app.services.broker;

import com.stinger.app.stinger.models.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//implements brokerconsumerservice<source>
public class KafkaConsumerService {
    private final SimpMessagingTemplate template;

//    @Override
//    @KafkaListener(topics = "news", groupId = "group_id")
//    public void consume(@Payload Source source) {
//        System.out.println("Consumed: " + source);
//        template.convertAndSend("/topic/greetings", source);
//    }
}
