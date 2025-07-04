package com.hd.sample_jpa_mysql_0605.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Builder
public class ChatRoomResDto { // 채팅방 개설에 대한 응답
    private String roomId;
    private String name;
    private LocalDateTime createdAt;

    @JsonIgnore // 이정보는 직렬화할때 빼라!!!!!!!!!!!
    private Set<WebSocketSession> sessions = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
}