package com.jason.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*").withSockJS();
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        
        // simple stomp broker (in memory)
        config.enableSimpleBroker("/topic");
        
        // stomp broker (using memory DB)
        /*config.enableStompBrokerRelay("/topic", "/user")
                .setRelayHost("192.168.2.134").setRelayPort(61613) // activemq
                .setSystemHeartbeatSendInterval(10000).setSystemHeartbeatReceiveInterval(10000);*/
        
        config.setApplicationDestinationPrefixes("/app");
    }
}