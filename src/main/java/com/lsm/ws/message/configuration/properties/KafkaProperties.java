package com.lsm.ws.message.configuration.properties;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Validated
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    @NotEmpty
    private String bootstrapServers;

    private Map<Topic, String> topics;

    public @NotEmpty String bootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(@NotEmpty String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public Map<Topic, String> topics() {
        return topics;
    }

    public void setTopics(Map<Topic, String> topics) {
        this.topics = topics;
    }

    public enum Topic {
        MESSAGE
    }
}
