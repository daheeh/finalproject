package com.highright.chattingtest.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highright.chattingtest.model.dto.ChatRoomDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

// 채팅방을 생성, 조회하고 하나의 세션에 메세지 발송하는 서비스
// 채팅방 Map : 서버에 생성된 모든 채팅방의 정보를 모아둠(일단 빠른 구현을 위해 DB말고 HashMap에 저장)
// 채팅방 조회 : 채팅방 Map에 담긴 정보를 조회
// 채팅방 생성 : Random UUID로 구별 ID를 가진 채팅방 객체를 생성하고 채팅방 Map에 추가
// 메세지 발송 : 지정한 WebSocket 세선에 메세시 발송

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDTO> chatRooms;

    @PostConstruct // 초기화 작업을 수행할 메서드에 @PostConstruct 어노테이션을 추가
    public void init() {
        chatRooms = new LinkedHashMap<>(); // LinkedHashMap: 일반 HashMap과 달리 추가된 요소들의 순서를 기억하고 유지
    }

    public List<ChatRoomDTO> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDTO findRoomById(String roomId){
        return chatRooms.get(roomId);
    }


    // 채팅방 name으로 새로운 채팅방 생성 후, 생성된 채팅방 객체 반환
    public ChatRoomDTO createRoom(String name) {
        String randomId = UUID.randomUUID().toString(); // 랜덤한 ID 생성(고유)
        ChatRoomDTO chatRoom = ChatRoomDTO.builder() // builder패턴 : 객체 생성시 매개변수 순서 신경쓸 필요 없음, 선택적으로 필드값 설정 가능
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom); // 생성된 방 객체 chatRooms맵에 저장(roomId를 key로 저장됨)
        return chatRoom;
    }


    // 제네릭 타입 T를 사용하여 다양한 타입의 메시지를 전송할 수 있도록 함
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
            // objectMapper.writeValueAsString(message) : objectMapper를 사용하여 메시지 객체(message)를 JSON 형식으로 변환
            // session.sendMessage(new TextMessage(jsonMessage)) : 변환된 JSON 형식의 메시지를 WebSocket 클라이언트에게 전송
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
