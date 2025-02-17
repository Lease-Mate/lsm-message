package com.lsm.ws.message.context;

import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final MessageService messageService;

    public MessageController(SimpMessageSendingOperations messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat.send-message")
    public void sendMessage(@Payload MessageDto messageDto, SimpMessageHeaderAccessor headerAccessor) {
        var chatMessage = messageDto.toMessage();
        chatMessage.setSessionId(headerAccessor.getSessionId());
        messageService.send(chatMessage);

        var destination = "/topic/public/" + chatMessage.chatId();
        messagingTemplate.convertAndSend(destination, MessageDto.from(chatMessage));
    }
}
