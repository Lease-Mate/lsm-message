package com.lsm.ws.message.infrastructure.rest.context;

public class RequestContext {

    private final String userId;

    public RequestContext(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}
