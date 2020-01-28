package com.ddm.flyem.controller;

import com.ddm.flyem.dao.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message message(Message message) {
        return message;
    }

}
