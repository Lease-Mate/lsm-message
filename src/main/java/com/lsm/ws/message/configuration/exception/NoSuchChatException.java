package com.lsm.ws.message.configuration.exception;

public class NoSuchChatException extends ValidationException {

    public NoSuchChatException() {
        super(ErrorCode.CHAT_DOES_NOT_EXIST);
    }
}
