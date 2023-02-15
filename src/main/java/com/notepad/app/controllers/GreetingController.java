package com.notepad.app.controllers;

import com.notepad.app.services.KafkaConsumerService;
import com.stinger.app.stinger.models.Source;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class GreetingController {
    private final KafkaConsumerService kafkaConsumerService;

    @Autowired
    public GreetingController(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(@Payload HelloMessage message) throws Exception {
//        log.debug("greeting() got message: " + message.getName());
//        Thread.sleep(1000);
//        log.debug("greeting() got message: " + message.getName());
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

    @MessageMapping("/hello")
    public Source getSource() {
        return kafkaConsumerService.consume();
    }
}
