package com.learn.messagingrabbitmq.controller;

import com.learn.messagingrabbitmq.util.RabbitMqUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessagingController {

    private final RabbitTemplate rabbitTemplate;

    public MessagingController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping()
    public ResponseEntity<?> sendMessage(@RequestBody String message){
    rabbitTemplate.convertAndSend(RabbitMqUtil.TOPIC_EXCHANGE_NAME, "foo.bar.baz", message);
        return ResponseEntity.ok(message);
    }

}
