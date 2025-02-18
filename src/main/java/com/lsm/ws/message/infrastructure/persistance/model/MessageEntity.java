package com.lsm.ws.message.infrastructure.persistance.model;

import com.lsm.ws.message.domain.message.Message;
import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "session_id")
    private String sessionId;

    public Message toMessage() {
        return new Message(id, chatId, senderId, content, timestamp, sessionId);
    }

    public void fromMessage(Message message) {
        setId(message.id());
        setChatId(message.chatId());
        setSenderId(message.sender());
        setContent(message.content());
        setTimestamp(message.timestamp());
        setSessionId(message.sessionId());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
