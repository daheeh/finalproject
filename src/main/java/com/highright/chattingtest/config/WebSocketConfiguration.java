package com.highright.chattingtest.config;

import com.highright.chattingtest.handler.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }

    // hanlder를 이용하여 WebSocket을 활성화하기 위한 Config파일을 작성한다.
    // WebSocket에 접속하기 위한 endpoint는 /ws/chat으로 설정하고 도메인이
    // 다른 서버에서도 접속 가능하도록 CORS: setAllowedOrgins("*)설정 추가!

}
