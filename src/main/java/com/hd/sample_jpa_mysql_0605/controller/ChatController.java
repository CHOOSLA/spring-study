package com.hd.sample_jpa_mysql_0605.controller;

import com.hd.sample_jpa_mysql_0605.dto.ChatRoomReqDto;
import com.hd.sample_jpa_mysql_0605.dto.ChatRoomResDto;
import com.hd.sample_jpa_mysql_0605.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/new")
    public ResponseEntity<String> createRoom(@RequestBody ChatRoomReqDto req) {
        ChatRoomResDto room = chatService.createRoom(req.getName());
        return ResponseEntity.ok(room.getRoomId());
    }

    @GetMapping("/list")
    public List<ChatRoomResDto> listRooms() {
        return chatService.getAllRooms();
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ChatRoomResDto> getRoom(@PathVariable String roomId) {
        return chatService.getRoomById(roomId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
