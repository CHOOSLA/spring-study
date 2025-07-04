package com.hd.sample_jpa_mysql_0605.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hd.sample_jpa_mysql_0605.dto.ChatMessageDto;
import com.hd.sample_jpa_mysql_0605.dto.ChatRoomResDto;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ObjectMapper objectMapper;
    private final Map<String, ChatRoomResDto> chatRooms = new LinkedHashMap<>();

    public ChatRoomResDto createRoom(String name) {
        String id = UUID.randomUUID().toString();
        ChatRoomResDto room = ChatRoomResDto.builder()
            .roomId(id)
            .name(name)
            .createdAt(LocalDateTime.now())
            .build();
        chatRooms.put(id, room);
        return room;
    }

    public void addSessionAndHandleEnter(String roomId, WebSocketSession session, ChatMessageDto msg) {
        ChatRoomResDto room = chatRooms.get(roomId);
        room.addSession(session);
        msg.setMessage(msg.getSender() + "님이 입장했습니다.");
        sendMessageToAll(roomId, msg);
    }

    public void sendMessageToAll(String roomId, ChatMessageDto msg) {
        ChatRoomResDto room = chatRooms.get(roomId);
        room.getSessions().forEach(s -> sendMessage(s, msg));
    }

    public void removeSessionAndHandleExit(String roomId, WebSocketSession session, ChatMessageDto msg) {
        ChatRoomResDto room = chatRooms.get(roomId);
        room.removeSession(session);
        msg.setMessage(msg.getSender() + "님이 퇴장했습니다.");
        sendMessageToAll(roomId, msg);

        if (room.getSessions().isEmpty()) {
            chatRooms.remove(roomId);
        }
    }

    private void sendMessage(WebSocketSession session, ChatMessageDto msg) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
        } catch (IOException | JsonProcessingException e) {
            log.error("메시지 전송 실패: {}", e.getMessage());
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ChatRoomResDto> getAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    public Optional<ChatRoomResDto> getRoomById(String roomId) {
        return Optional.ofNullable(chatRooms.get(roomId));
    }
}

