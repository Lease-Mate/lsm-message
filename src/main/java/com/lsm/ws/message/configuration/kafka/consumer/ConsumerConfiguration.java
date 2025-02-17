package com.lsm.ws.message.configuration.kafka.consumer;

import com.lsm.ws.message.configuration.kafka.KafkaSerdes;
import com.lsm.ws.message.configuration.properties.KafkaProperties;
import com.lsm.ws.message.infrastructure.kafka.dto.MessageDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfiguration {

    private final KafkaProperties kafkaProperties;
    private final KafkaSerdes kafkaSerdes;

    public ConsumerConfiguration(KafkaProperties kafkaProperties, KafkaSerdes kafkaSerdes) {
        this.kafkaProperties = kafkaProperties;
        this.kafkaSerdes = kafkaSerdes;
    }

    @Bean
    public ConsumerFactory<String, MessageDto> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers());
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "chat");
        return new DefaultKafkaConsumerFactory<>(
                configProps,
                new StringDeserializer(),
                kafkaSerdes.messageSerde.deserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
