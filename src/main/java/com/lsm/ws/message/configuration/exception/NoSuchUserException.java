package com.lsm.ws.message.configuration.exception;

public class NoSuchUserException extends ValidationException {

    public NoSuchUserException() {
        super(ErrorCode.USER_DOES_NOT_EXIST);
    }
}
