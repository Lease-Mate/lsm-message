package com.lsm.ws.message.infrastructure.persistance.jpa;

import com.lsm.ws.message.infrastructure.persistance.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, String>,
        JpaSpecificationExecutor<MessageEntity> {
}
