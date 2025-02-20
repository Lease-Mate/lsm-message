package com.lsm.ws.message.domain.message;

import java.util.Objects;

public record Chat(
        String chatId,
        String userAId,
        String userBId
) {
    public UserChat toUserChat(String loggedUserId) {
        var otherUserId = Objects.equals(loggedUserId, userAId)
                ? userBId
                : userAId;
        return new UserChat(chatId, otherUserId);
    }
}
