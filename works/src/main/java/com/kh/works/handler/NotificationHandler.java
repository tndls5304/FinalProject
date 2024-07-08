package com.kh.works.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

//알림 기능 - Socket 환경설정

public class NotificationHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("WebSocket session added: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("WebSocket session removed: " + session.getId());
    }

    public void sendNotification(String message) {
        for (WebSocketSession session : sessions) {
            try {
                System.out.println("Sending message to session " + session.getId() + ": " + message);
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to send notification: " + e.getMessage());
            }
        }
    }
}
