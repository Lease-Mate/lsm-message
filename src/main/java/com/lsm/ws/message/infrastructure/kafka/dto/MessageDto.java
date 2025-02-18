package com.lsm.ws.message.infrastructure.kafka.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lsm.ws.message.domain.message.Message;

import java.time.LocalDateTime;

public class MessageDto {

    public String id;
    public String chatId;
    public String sender;
    public String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime timestamp;
    public String sessionId;

    public static MessageDto from(Message message) {
        MessageDto dto = new MessageDto();
        dto.id = message.id();
        dto.chatId = message.chatId();
        dto.sender = message.sender();
        dto.content = message.content();
        dto.timestamp = message.timestamp();
        dto.sessionId = message.sessionId();
        return dto;
    }

    public Message toMessage() {
        return new Message(id, chatId, sender, content, timestamp, sessionId);
    }
}
