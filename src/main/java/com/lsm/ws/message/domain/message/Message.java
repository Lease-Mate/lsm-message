package com.lsm.ws.message.domain.message;

import java.time.LocalDateTime;

public final class Message {

    private String id;
    private String chatId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private String sessionId;

    public Message() {
    }

    public Message(
            String id, String chatId,
            String sender,
            String content,
            LocalDateTime timestamp,
            String sessionId
    ) {
        this.id = id;
        this.chatId = chatId;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
        this.sessionId = sessionId;
    }

    public String id() {
        return id;
    }

    public String chatId() {
        return chatId;
    }

    public String sender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String content() {
        return content;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public String sessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
