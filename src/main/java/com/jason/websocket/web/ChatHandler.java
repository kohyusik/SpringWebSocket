package com.jason.websocket.web;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class ChatHandler implements WebSocketHandler {
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    
        System.out.println("@@@");
    }
    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    
        System.out.println("handleMessage");
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    
        System.out.println("afterConnectionClosed");
    }
    
    @Override
    public boolean supportsPartialMessages() {
        
        return false;
    }
}