package com.hd.sample_jpa_mysql_0605.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hd.sample_jpa_mysql_0605.dto.ChatMessageDto;
import com.hd.sample_jpa_mysql_0605.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper; // 메시지의 역직렬화 , 의존성의 주입

    // 세션별 채팅방 채팅방 ID 저장
    private final Map<WebSocketSession, String> sessionRoomMap = new ConcurrentHashMap<>();
    private ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 1) 수신된 JSON을 DTO로 역직렬화
        ChatMessageDto dto = objectMapper.readValue(message.getPayload(), ChatMessageDto.class);
        String roomId = dto.getRoomId();

        // 2) 메시지 타입에 따라 분기 처리
        switch (dto.getType()) {
            case ENTER:
                sessionRoomMap.put(session, roomId);
                chatService.addSessionAndHandleEnter(roomId, session, dto);
                break;

            case TALK:
                chatService.sendMessageToAll(roomId, dto);
                break;

            case CLOSE:
                chatService.removeSessionAndHandleExit(roomId, session, dto);
                break;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 종료 시 채팅방에서 자동 퇴장 처리
        String roomId = sessionRoomMap.remove(session);
        if (roomId != null) {
            chatService.removeSessionAndHandleExit(roomId, session,
                ChatMessageDto.builder()
                    .type(ChatMessageDto.MessageType.CLOSE)
                    .roomId(roomId)
                    .sender("System")
                    .message("연결이 끊어졌습니다.")
                    .build());
        }
    }
}
