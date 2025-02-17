package com.lsm.ws.message.infrastructure.persistance.model;

import com.lsm.ws.message.domain.message.Chat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat")
public class ChatEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_a")
    private String userA;

    @Column(name = "user_b")
    private String userB;

    public Chat toChat() {
        return new Chat(id, userA, userB);
    }

    public void fromChat(Chat chat) {
        setId(chat.chatId());
        setUserA(chat.userAId());
        setUserB(chat.userBId());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserA(String userA) {
        this.userA = userA;
    }

    public void setUserB(String userB) {
        this.userB = userB;
    }
}
