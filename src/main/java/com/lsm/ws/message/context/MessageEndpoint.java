package com.lsm.ws.message.context;

import com.lsm.ws.message.context.dto.IdWrapperDto;
import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import com.lsm.ws.message.infrastructure.rest.PaginationSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/api/message")
@Tag(name = "Wiadomości")
public class MessageEndpoint {

    private final MessageService messageService;

    public MessageEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Pozyskaj czat", description = "Zwraca id czatu dla danego użytkownika, wymaga tokenu JWT")
    @GetMapping("/{userId}/chat")
    public ResponseEntity<IdWrapperDto> getChat(@PathVariable String userId) {
        var chat = messageService.getOrCreateChat(userId);
        return ResponseEntity.ok(IdWrapperDto.from(chat.chatId()));
    }

    @Operation(hidden = true)
    @GetMapping("/internal/{userIdA}/{userIdB}/chat")
    public ResponseEntity<IdWrapperDto> getInternalChat(@PathVariable String userIdA, @PathVariable String userIdB) {
        var chat = messageService.getOrCreateChat(userIdA, userIdB);
        return ResponseEntity.ok(IdWrapperDto.from(chat.chatId()));
    }

    @Operation(summary = "Pozyskaj wiadomości", description = "Zwraca wiadomości dla danego czatu")
    @GetMapping("/chat/{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable String chatId,
                                                        @ParameterObject
                                                        PaginationSpecification paginationSpecification) {
        var messages = messageService.getMessages(chatId, paginationSpecification);
        return ResponseEntity.ok(messages.stream().map(MessageDto::from)
                                         .toList());
    }
}
