package com.lsm.ws.message.configuration.exception;

public enum ErrorCode {

    USER_DOES_NOT_EXIST("001", "Użytkownik nie istnieje"),
    CHAT_DOES_NOT_EXIST("002", "Czat nie istnieje"),
    ;

    private static final String MICROSERVICE_PREFIX = "lsm-message-";
    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = MICROSERVICE_PREFIX + code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
