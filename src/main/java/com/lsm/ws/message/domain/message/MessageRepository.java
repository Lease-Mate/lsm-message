package com.lsm.ws.message.domain.message;

import com.lsm.ws.message.domain.Pagination;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);

    List<Message> getMessagesByChatId(String chatId, Pagination pagination);
}
