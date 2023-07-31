package com.highright.chattingtest.model.dto;

import lombok.*;

// 채팅 메세지를 주고받기 위한 DTO
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatDTO {

    // 메세지 타입 : 입장 메세지
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type; // 메세지 타입
    private String roomId; // 방 번호
    private String Sender; // 채팅 보낸 사람
    private String message; // 메세지
//    private String time; // 채팅 보낸 시간
}
