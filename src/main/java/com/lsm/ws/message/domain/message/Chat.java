package com.lsm.ws.message.domain.message;

public record Chat(
        String chatId,
        String userAId,
        String userBId
) {
}
