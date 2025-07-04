package com.hd.sample_jpa_mysql_0605.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import com.hd.sample_jpa_mysql_0605.config.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler,"ws/chat").setAllowedOrigins("*");
    }
}
