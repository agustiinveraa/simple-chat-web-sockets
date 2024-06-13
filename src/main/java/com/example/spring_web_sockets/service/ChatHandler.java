package com.example.spring_web_sockets.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

// Este chat handler es el que se encarga de administrar las conexiones hacia nuestro web socket
@Service
public class ChatHandler extends TextWebSocketHandler {

    // Tipo de lista que esta diseñado para ser eficiente en entornos donde hay concurrencia como en este chat
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();

    // una vez abierta la conexión
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    // una vez cerrada la conexión
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    // una vez desde el cliente llegue un mensaje a este handleTextMessage llegará aquí y este se enviara a todas las sesiones activas (lista SESSIONS de arriba)
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(message);
        }
    }
}
