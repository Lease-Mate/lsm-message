package com.lsm.ws.message.infrastructure.kafka;

import com.lsm.ws.message.configuration.properties.KafkaProperties;
import com.lsm.ws.message.domain.message.Message;
import com.lsm.ws.message.domain.message.MessagePublisher;
import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessagePublisher implements MessagePublisher {

    private final KafkaTemplate<String, MessageDto> messageKafkaTemplate;
    private final String messageTopic;

    public KafkaMessagePublisher(KafkaTemplate<String, MessageDto> messageKafkaTemplate,
                                 KafkaProperties kafkaProperties) {
        this.messageKafkaTemplate = messageKafkaTemplate;
        this.messageTopic = kafkaProperties.topics().get(KafkaProperties.Topic.MESSAGE);
    }

    @Override
    public void publish(Message message) {
        var record = new ProducerRecord<>(messageTopic, message.chatId(), MessageDto.from(message));
        messageKafkaTemplate.send(record);
    }
}
