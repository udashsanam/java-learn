package com.learn.multilanguage.controller;

import com.learn.multilanguage.config.CustomMessageSource;
import com.learn.multilanguage.dto.SayHello;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final  CustomMessageSource messageSource;

    public TestController(CustomMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostMapping
    public String test(@RequestBody Object test){

        return messageSource.get("create");
    }

    @PostMapping("/say-hello")
    public String sayHello(@RequestBody SayHello sayHello){

       return messageSource.get("say.hello", sayHello.getFrom(), sayHello.getTo());
    }
}
