package com.highright.chattingtest.model.dto;

import com.highright.chattingtest.model.service.ChatService;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;


// 채팅방 구현을 위한 DTO
// 입장한 클라이언트들의 정보를 가지고 있어야 함
// 입장 시 채팅방 session정보에 클라이언트의 session리스트 추가
// 채팅방에는 입장, 대화하기 기능이 있으므로 handleAction을 통해 분기처리한다
@RequiredArgsConstructor
@Getter
@Setter
public class ChatRoomDTO {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDTO(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleAction(WebSocketSession session, ChatDTO chat, ChatService chatService){
        if(chat.getType().equals(ChatDTO.MessageType.ENTER)) {
            sessions.add(session);
            chat.setMessage(chat.getSender() + "님이 입장하셨습니다.");
        }
        sendMessage(chat, chatService);
    }
    // ㄴ> 먼저 chatDTO 객체의 MessageType을 확인 => 메시지 유형이 ENTER인 경우, 이는 새로운 사용자가 채팅 방에 입장한 것을 의미하므로,
    // 현재 WebSocket 세션 (session)을 sessions 집합에 추가 => sessions 집합은 모든 연결된 클라이언트를 추적
    //  마지막으로 수정된 chatDTO 메시지를 sendMessage 메서드를 호출하여 모든 연결된 클라이언트에게 브로드캐스트

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
    // ㄴ> <T> :  제네릭 타입 매개변수로, <T>는 임의의 타입을 나타내며, 메서드를 호출할 때 실제 타입으로 치환됨
    // sendMessage 메서드에서 <T>는 전달받은 message 매개변수의 타입 => 서드를 호출할 때 T에 어떤 타입의 객체를 전달하느냐에 따라 해당 타입으로 치환되어 메서드가 실행
}
