package com.highright.chattingtest.controller;

import com.highright.chattingtest.model.dto.ChatDTO;
import com.highright.chattingtest.model.dto.ChatRoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    // @MessageMapping을 통해 WebSocket으로 들어오는 메세지 발행을 처리함
    // 클라이언트에서는 prefix를 붙여서 /pub/chat/message로 발행 요청을 하면 Controller가 해당 메세지를 받아서 처리함

    @MessageMapping("/chat/message")
    public void message(ChatDTO message) {
        if(ChatDTO.MessageType.JOIN.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
