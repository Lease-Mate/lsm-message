package com.lsm.ws.message.domain.message;

import java.time.LocalDateTime;

public final class Message {

    private String chatId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private String sessionId;
    private MessageType messageType;

    public Message() {
    }

    public Message(
            String chatId,
            String sender,
            String content,
            LocalDateTime timestamp,
            String sessionId,
            MessageType messageType
    ) {
        this.chatId = chatId;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
        this.sessionId = sessionId;
        this.messageType = messageType;
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

    public MessageType messageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
