package com.highright.chattingtest.model.dto;

import lombok.*;

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

    public static ChatRoomDTO create(String name) {
        ChatRoomDTO chatRoom = new ChatRoomDTO();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

}
