package com.lsm.ws.message.domain.message;

import java.util.Optional;

public interface ChatRepository {

    Optional<Chat> findChatWithUsers(String userAId, String userBId);

    Chat save(Chat newChat);
}
