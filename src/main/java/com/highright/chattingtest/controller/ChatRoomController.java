package com.highright.chattingtest.controller;

import com.highright.chattingtest.model.dto.ChatRoomDTO;
import com.highright.chattingtest.repository.ChatRoomRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
@Slf4j
// 채팅화면 View 구성을 위해 필요한 Controller
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;


    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list", chatRoomRepository.findAllRooms());

        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){

        log.info("# Create Chat Room , name: " + name);
        rttr.addFlashAttribute("roomName", chatRoomRepository.createChatRoomDTO(name));
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", chatRoomRepository.findRoomById(roomId));
    }










//    // 채팅방 목록 화면
//    @GetMapping("/room")
//    public String rooms(Model model){
//
//        return "/chat/room";
//    }
//
//    // 모든 채팅방 목록 반환
//    @GetMapping("/rooms")
//    @ResponseBody
//    public List<ChatRoomDTO> room(){
//
//        return chatRoomRepository.findAllRoom();
//    }
//
//    // 채팅방 생성
//    @PostMapping("/room")
//    @ResponseBody
//    public ChatRoomDTO createRoom(@RequestParam String name){
//        return chatRoomRepository.createChatRoom(name);
//    }
//
//    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId){
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
//
//    // 특정 채팅방 조회
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoomDTO roomInfo(@PathVariable String roomId){
//        return chatRoomRepository.findRoomById(roomId);
//    }
//
}
