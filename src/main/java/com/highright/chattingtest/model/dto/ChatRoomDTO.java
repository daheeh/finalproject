package com.highright.chattingtest.model.dto;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@RequiredArgsConstructor
@Getter
@Setter
public class ChatRoomDTO {

    // pub/sub방식을 이용하면 구독자 관리가 알아서 되므로 웹소켓 세션 관리가 필요없어짐
    // 발송의 구현도 알아서 해결되므로 일일이 클라이언트에게 메세지를 발송하는 구현이 필요 없어짐
    // 따라서 STOMP를 사용하지 않았을 때의 DTO에 비해 간소화됨

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static ChatRoomDTO create(String name){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }

}
