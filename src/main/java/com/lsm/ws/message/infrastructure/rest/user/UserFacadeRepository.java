package com.lsm.ws.message.infrastructure.rest.user;

import com.lsm.ws.message.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserFacadeRepository implements UserRepository {

    private final UserClient userClient;

    public UserFacadeRepository(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public void verifyToken(String token) {
        userClient.verify(token);
    }

    @Override
    public Boolean exist(String userId) {
        return userClient.exists(userId);
    }
}
