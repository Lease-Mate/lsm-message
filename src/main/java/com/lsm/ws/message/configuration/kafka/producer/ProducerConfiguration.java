package com.lsm.ws.message.configuration.kafka.producer;

import com.lsm.ws.message.configuration.kafka.KafkaSerdes;
import com.lsm.ws.message.configuration.properties.KafkaProperties;
import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@EnableConfigurationProperties(KafkaProperties.class)
@Configuration
public class ProducerConfiguration {

    private final KafkaProperties kafkaProperties;

    public ProducerConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    KafkaTemplate<String, MessageDto> messageKafkaTemplate(KafkaSerdes kafkaSerdes) {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(
                producerConfigs(),
                new StringSerializer(),
                kafkaSerdes.messageSerde.serializer()));
    }

    private Map<String, Object> producerConfigs() {
        return Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers());
    }
}
