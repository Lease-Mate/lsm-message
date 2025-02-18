package com.lsm.ws.message.context;

import com.lsm.ws.message.configuration.exception.forbidden.ForbiddenException;
import com.lsm.ws.message.domain.message.Chat;
import com.lsm.ws.message.infrastructure.rest.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class UserAccessValidator {

    private final RequestContext requestContext;

    public UserAccessValidator(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public void validateAccess(Chat chat) {
        if (chat.userAId().equals(requestContext.userId()) || chat.userBId().equals(requestContext.userId())) {
            return;
        }
        throw new ForbiddenException("Nie masz dostępu do tego czatu");
    }
}
