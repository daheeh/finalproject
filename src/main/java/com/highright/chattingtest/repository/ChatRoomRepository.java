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

    private Map<String, ChatRoomDTO> chatRoomDTOMap;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDTO findRoomById(String id){
        return chatRoomDTOMap.get(id);
    }

    public ChatRoomDTO createChatRoomDTO(String name){
        ChatRoomDTO room = ChatRoomDTO.create(name);
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }

}
