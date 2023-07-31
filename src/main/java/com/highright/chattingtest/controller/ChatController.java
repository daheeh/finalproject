package com.highright.chattingtest.controller;

import com.highright.chattingtest.model.dto.ChatRoomDTO;
import com.highright.chattingtest.model.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // (@Controller + @ResponseBody)
@RequestMapping("/chat")

// 채팅방 생성 및 조회는 Rest api로 구현
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoomDTO createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDTO> findAllRoom() {
        return chatService.findAllRoom();
    }
}
