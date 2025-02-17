package com.lsm.ws.message.infrastructure.rest.context;

import com.lsm.ws.message.configuration.exception.unauthorized.JwtAuthenticationException;
import com.lsm.ws.message.infrastructure.jwt.JwtClaims;
import com.lsm.ws.message.infrastructure.jwt.JwtExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class RequestContextFactory {
    private final JwtExtractor jwtExtractor;
    private final HttpServletRequest request;

    public RequestContextFactory(JwtExtractor jwtExtractor, HttpServletRequest request) {
        this.jwtExtractor = jwtExtractor;
        this.request = request;
    }

    @RequestScope
    @Bean
    RequestContext requestContext() {
        if (!jwtExtractor.isAuthHeaderPresent(request)) {
            return new RequestContext(null);
        }

        var jwt = jwtExtractor.extractJwtFromRequest(request);
        var claims = jwtExtractor.validateTokenAndExtractClaims(jwt);

        var userId = jwtExtractor.extractClaim(JwtClaims.USER_ID, claims)
                                 .orElseThrow(() -> new JwtAuthenticationException(
                                         "Error during extracting user id"
                                 ));
        return new RequestContext(userId);
    }
}
