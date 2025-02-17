package com.lsm.ws.message.context;

import com.lsm.ws.message.context.dto.IdWrapperDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/v1/api/message")
@Tag(name = "Message services")
public class MessageEndpoint {

    private final MessageService messageService;

    public MessageEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Send message", description = "Sends message to specified user")
    @GetMapping("/{userId}/chat")
    public ResponseEntity<IdWrapperDto> send(@PathVariable String userId) {
        var chat = messageService.getOrCreateChat(userId);
        return ResponseEntity.ok(IdWrapperDto.from(chat.chatId()));
    }

    @Operation(summary = "Send message", description = "Sends message to specified user")
    @GetMapping("/internal/{userIdA}/{userIdB}/chat")
    public ResponseEntity<IdWrapperDto> getInternalChat(@PathVariable String userIdA, @PathVariable String userIdB) {
        var chat = messageService.getOrCreateChat(userIdA, userIdB);
        return ResponseEntity.ok(IdWrapperDto.from(chat.chatId()));
    }
}
