package com.lsm.ws.message.configuration.exception.unauthorized;

public class JwtAuthenticationException extends UnauthorizedException {

    public JwtAuthenticationException(String message) {
        super(message);
    }
}
