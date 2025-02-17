package com.lsm.ws.message.infrastructure.persistance.jpa;

import com.lsm.ws.message.infrastructure.persistance.model.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChatJpaRepository extends JpaRepository<ChatEntity, String>, JpaSpecificationExecutor<ChatEntity> {
}
