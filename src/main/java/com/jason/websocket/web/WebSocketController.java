package com.jason.websocket.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate template;
    
    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }
    
    
    @MessageMapping("/chat/join")
    public void join(Object message) {
    
        System.out.println();
//        template.convertAndSend("/subscribe/chat" + message.getChatRoomId(), message);
    }
}