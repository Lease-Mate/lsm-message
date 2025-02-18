package com.lsm.ws.message.infrastructure.facade;

import com.lsm.ws.message.domain.Pagination;
import com.lsm.ws.message.domain.message.Message;
import com.lsm.ws.message.domain.message.MessageRepository;
import com.lsm.ws.message.infrastructure.persistance.jpa.MessageJpaRepository;
import com.lsm.ws.message.infrastructure.persistance.model.MessageEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageFacadeRepository implements MessageRepository {

    private final MessageJpaRepository messageJpaRepository;

    public MessageFacadeRepository(MessageJpaRepository messageJpaRepository) {
        this.messageJpaRepository = messageJpaRepository;
    }

    @Override
    public Message save(Message message) {
        var entity = new MessageEntity();
        entity.fromMessage(message);
        return messageJpaRepository.save(entity)
                                   .toMessage();
    }

    @Override
    public List<Message> getByChatId(String chatId, Pagination pagination) {
        var spec = Specification.where(hasChatId(chatId));
        var pageable = PageRequest.of(
                pagination.getPage() - 1,
                pagination.getSize(),
                Sort.by(Sort.Direction.ASC, "timestamp")
        );
        return messageJpaRepository.findAll(spec, pageable)
                                   .map(MessageEntity::toMessage)
                                   .toList();
    }

    private Specification<MessageEntity> hasChatId(String chatId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("chatId"), chatId);
    }
}
