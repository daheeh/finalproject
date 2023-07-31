package com.highright.chattingtest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highright.chattingtest.model.dto.ChatDTO;
import com.highright.chattingtest.model.dto.ChatRoomDTO;
import com.highright.chattingtest.model.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    // socket통신은 서버와 클라이언트가 1:N관계를 맺음, 따라서 한 서버에 여러 클라이언트가 접속할 수 있으며,
    // 서버에는 여러 클라이언트가 발송한 메세지를 받아 처리해줄 Handler의 작성이 필요함

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        log.info("payload {}", payload);
    // WebSocket클라이언트로부터 채팅 메세지 전달받아 채팅 메세지 객체로 변환
        ChatDTO chat = objectMapper.readValue(payload, ChatDTO.class);
    // 전달받은 메세지에 담긴 채팅방 Id로 발송 대상 채팅방 정보를 조회
        ChatRoomDTO room = chatService.findRoomById(chat.getRoomId());
    // 해당 채팅방에 입장해있는 모든 클라이언트들(WebSocket session)에게 타입에 따른 메세지 발송
        room.handleAction(session, chat, chatService);
    }
}
