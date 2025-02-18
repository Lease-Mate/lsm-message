package com.lsm.ws.message.infrastructure.kafka;

import com.lsm.ws.message.domain.message.MessageRepository;
import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {

    private final SimpMessageSendingOperations messagingTemplate;
    private final SimpUserRegistry userRegistry;
    private final MessageRepository messageRepository;

    public KafkaMessageConsumer(SimpMessageSendingOperations messagingTemplate, SimpUserRegistry userRegistry,
                                MessageRepository messageRepository) {
        this.messagingTemplate = messagingTemplate;
        this.userRegistry = userRegistry;
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics = "${kafka.topics.message}", groupId = "chat")
    public void listen(MessageDto messageDto) {
        for (SimpUser user : userRegistry.getUsers()) {
            for (SimpSession session : user.getSessions()) {
                if (!session.getId().equals(messageDto.sessionId)) {
                    messagingTemplate.convertAndSendToUser(session.getId(), "/topic/public", messageDto);
                }
            }
        }
    }

    @KafkaListener(topics = "${kafka.topics.message}", groupId = "persist")
    public void listenPersist(MessageDto messageDto) {
        messageRepository.save(messageDto.toMessage());
    }
}
