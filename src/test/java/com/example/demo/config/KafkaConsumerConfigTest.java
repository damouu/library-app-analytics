package com.example.demo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class KafkaConsumerConfigTest extends KafkaConsumerConfig {

    private final KafkaConsumerConfig config = new KafkaConsumerConfig();

    @Test
    void shouldReturnCorrectBaseConsumerConfig() {
        String mockServers = "localhost:9092";
        ReflectionTestUtils.setField(config, "bootstrapServers", mockServers);

        Map<String, Object> props = config.baseConsumerConfig();


        assertThat(props.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG)).isEqualTo(mockServers);
        assertThat(props.get(ConsumerConfig.GROUP_ID_CONFIG)).isEqualTo("analytics-group");
        assertThat(props.get(JsonDeserializer.TRUSTED_PACKAGES)).isEqualTo("com.example.demo.dto");


        assertThat(props.get(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG)).isEqualTo(ErrorHandlingDeserializer.class);
        assertThat(props.get(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS)).isEqualTo(UUIDDeserializer.class);
        assertThat(props.get(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS)).isEqualTo(JsonDeserializer.class);
    }

    @Test
    void shouldCreateConsumerFactory() {
        ReflectionTestUtils.setField(config, "bootstrapServers", "localhost:9092");

        ConsumerFactory<Object, Object> factory = config.consumerFactory();

        assertThat(factory).isNotNull();
        assertThat(factory.getConfigurationProperties()).containsEntry(ConsumerConfig.GROUP_ID_CONFIG, "analytics-group");
    }

    @Test
    void shouldCreateContainerFactoryWithGivenConsumerFactory() {

        ConsumerFactory<Object, Object> mockConsumerFactory = mock(ConsumerFactory.class);

        ConcurrentKafkaListenerContainerFactory<Object, Object> resultFactory = config.factory(mockConsumerFactory);

        assertThat(resultFactory).isNotNull();

        assertThat(resultFactory.getConsumerFactory()).isSameAs(mockConsumerFactory);
    }

}