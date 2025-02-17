package com.lsm.ws.message.context;

import com.lsm.ws.message.domain.message.Chat;
import com.lsm.ws.message.domain.message.ChatRepository;
import com.lsm.ws.message.domain.message.Message;
import com.lsm.ws.message.domain.message.MessagePublisher;
import com.lsm.ws.message.infrastructure.rest.context.RequestContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    private final ChatRepository chatRepository;
    private final RequestContext requestContext;
    private final MessagePublisher messagePublisher;

    public MessageService(ChatRepository chatRepository, RequestContext requestContext,
                          MessagePublisher messagePublisher) {
        this.chatRepository = chatRepository;
        this.requestContext = requestContext;
        this.messagePublisher = messagePublisher;
    }

    public void send(Message message) {
        messagePublisher.publish(message);
    }

    public Chat getOrCreateChat(String userId) {
        return getOrCreateChat(requestContext.userId(), userId);
    }

    public Chat getOrCreateChat(String userIdA, String userIdB) {
        var existingChat = chatRepository.findChatWithUsers(userIdA, userIdB);
        if (existingChat.isPresent()) {
            return existingChat.get();
        }

        var newChat = new Chat(
                UUID.randomUUID().toString(),
                userIdA,
                userIdB
        );
        return chatRepository.save(newChat);
    }
}
