package com.lsm.ws.message.infrastructure.facade;

import com.lsm.ws.message.domain.Pagination;
import com.lsm.ws.message.domain.message.Chat;
import com.lsm.ws.message.domain.message.ChatRepository;
import com.lsm.ws.message.infrastructure.persistance.jpa.ChatJpaRepository;
import com.lsm.ws.message.infrastructure.persistance.model.ChatEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public Optional<Chat> findById(String chatId) {
        return chatJpaRepository.findById(chatId)
                                .map(ChatEntity::toChat);
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

    @Override
    public List<Chat> getByUserId(String userId, Pagination pagination) {
        var pageable = PageRequest.of(
                pagination.getPage() - 1,
                pagination.getSize()
        );
        var spec = Specification.where(hasUserId(userId));
        return chatJpaRepository.findAll(spec, pageable)
                                .map(ChatEntity::toChat)
                                .stream().toList();
    }

    private Specification<ChatEntity> hasUserId(String userId) {
        Specification<ChatEntity> userA = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userA"), userId);
        Specification<ChatEntity> userB = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userB"), userId);

        return userA.or(userB);
    }
}
