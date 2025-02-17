package com.lsm.ws.message.configuration.exception.dto;

public class ErrorCodeResponse extends ErrorResponse {

    public final String code;

    public ErrorCodeResponse(String code, String message) {
        super(message);
        this.code = code;
    }
}
