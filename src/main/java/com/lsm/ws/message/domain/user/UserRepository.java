package com.lsm.ws.message.domain.user;

public interface UserRepository {

    void verifyToken(String token);

    Boolean exist(String userId);
}
