package com.ddm.flyem.controller;

import com.ddm.flyem.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message/{token}")
    public void message(@DestinationVariable String token, Message message) {
        simpMessagingTemplate.convertAndSend("/topic/messages/" + token, message);
    }

}
