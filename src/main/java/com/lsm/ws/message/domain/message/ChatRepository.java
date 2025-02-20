package com.lsm.ws.message.domain.message;

import com.lsm.ws.message.domain.Pagination;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {

    Optional<Chat> findChatWithUsers(String userAId, String userBId);

    Chat save(Chat newChat);

    Optional<Chat> findById(String chatId);

    List<Chat> getByUserId(String userId, Pagination pagination);
}
