package com.highright.chattingtest.repository;


import com.highright.chattingtest.model.dto.ChatRoomDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.*;

// ChatService를 대체
// 여기서는 채팅방 정보를 Map으로 저장하지만, 만약 DB에 저장하고싶으면 서비스와 레파지토리 모두 필요
@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDTO> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoomDTO findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoomDTO createChatRoom(String name) {
        ChatRoomDTO chatRoom = ChatRoomDTO.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
