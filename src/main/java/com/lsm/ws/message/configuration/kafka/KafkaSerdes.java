package com.lsm.ws.message.configuration.kafka;

import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
public class KafkaSerdes {

    public final JsonSerde<MessageDto> messageSerde = new JsonSerde<>(MessageDto.class);

    public Serde<MessageDto> messageSerde() {
        return messageSerde;
    }
}
