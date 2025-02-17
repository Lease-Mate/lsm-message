package com.lsm.ws.message.infrastructure.facade;

import com.lsm.ws.message.domain.message.Chat;
import com.lsm.ws.message.domain.message.ChatRepository;
import com.lsm.ws.message.infrastructure.persistance.jpa.ChatJpaRepository;
import com.lsm.ws.message.infrastructure.persistance.model.ChatEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatFacadeRepository implements ChatRepository {

    private final ChatJpaRepository chatJpaRepository;

    public ChatFacadeRepository(ChatJpaRepository chatJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
    }

    @Override
    public Optional<Chat> findChatWithUsers(String userAId, String userBId) {
        return chatJpaRepository.findAll(hasUsers(userAId, userBId))
                                .stream().findFirst()
                                .map(ChatEntity::toChat);
    }

    @Override
    public Chat save(Chat newChat) {
        var entity = new ChatEntity();
        entity.fromChat(newChat);
        return chatJpaRepository.save(entity).toChat();
    }

    Specification<ChatEntity> hasUsers(String userAId, String userBId) {
        Specification<ChatEntity> userA = (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.equal(root.get("userA"), userAId),
                criteriaBuilder.equal(root.get("userA"), userBId)
        );
        Specification<ChatEntity> userB = (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.equal(root.get("userB"), userAId),
                criteriaBuilder.equal(root.get("userB"), userBId)
        );
        return Specification.where(userA).and(userB);
    }
}
