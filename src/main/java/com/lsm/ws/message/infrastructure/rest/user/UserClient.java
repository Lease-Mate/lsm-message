package com.lsm.ws.message.infrastructure.rest.user;

import com.lsm.ws.message.configuration.exception.unauthorized.UnauthorizedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class UserClient {

    private final String AUTH_HEADER = "Authorization";
    private final String VERIFY_URI = "/v1/api/user/internal/auth/verify";
    private final String EXISTS_URI = "/v1/api/user/internal/{userId}/exists";

    private final WebClient webClient;

    public UserClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public void verify(String token) {
        webClient.post().uri(VERIFY_URI)
                 .header(AUTH_HEADER, "Bearer " + token)
                 .retrieve()
                 .onStatus(HttpStatusCode::isError, unauthorizedErrorHandler())
                 .toEntity(String.class)
                 .block();
    }

    public Boolean exists(String userId) {
        return webClient.get().uri(EXISTS_URI, userId)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, defaultErrorHandler())
                        .toEntity(Boolean.class)
                        .blockOptional()
                        .map(HttpEntity::getBody)
                        .orElse(false);
    }

    private Function<ClientResponse, Mono<? extends Throwable>> unauthorizedErrorHandler() {
        return (response) -> response.bodyToMono(Message.class).handle(
                (error, sink) -> sink.error(new UnauthorizedException(error.message))
        );
    }

    private Function<ClientResponse, Mono<? extends Throwable>> defaultErrorHandler() {
        return (response) -> response.bodyToMono(Message.class).handle(
                (error, sink) -> sink.error(new RuntimeException(error.message))
        );
    }

    static class Message {

        public String message;
    }
}
