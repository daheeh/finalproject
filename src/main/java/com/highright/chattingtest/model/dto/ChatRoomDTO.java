package com.highright.chattingtest.model.dto;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@RequiredArgsConstructor
@Getter
@Setter
public class ChatRoomDTO implements Serializable {

    private static final long serialVersionUID = 6494678977089006638L;

    private String roomId;
    private String name;

    public static ChatRoomDTO create(String name){
        ChatRoomDTO room = new ChatRoomDTO();
        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }

}
