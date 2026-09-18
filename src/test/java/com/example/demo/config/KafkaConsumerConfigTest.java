package com.example.demo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KafkaConsumerConfigTest {

    private KafkaConsumerConfig config;
    private KafkaProperties kafkaProperties;

    @BeforeEach
    void setUp() {
        kafkaProperties = mock(KafkaProperties.class);

        Map<String, Object> baseProperties = new HashMap<>();
        baseProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        when(kafkaProperties.buildConsumerProperties()).thenReturn(baseProperties);

        config = new KafkaConsumerConfig(kafkaProperties);
    }

    @Test
    void shouldReturnCorrectBaseConsumerConfig() {
        Map<String, Object> props = config.baseConsumerConfig();

        assertThat(props.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG)).isEqualTo("localhost:9092");

        assertThat(props.get(ConsumerConfig.GROUP_ID_CONFIG)).isEqualTo("analytics-group");

        assertThat(props.get(JsonDeserializer.TRUSTED_PACKAGES)).isEqualTo("com.example.demo.dto");

        assertThat(props.get(JsonDeserializer.USE_TYPE_INFO_HEADERS)).isEqualTo(true);

        assertThat(props.get(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG)).isEqualTo(ErrorHandlingDeserializer.class);

        assertThat(props.get(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG)).isEqualTo(ErrorHandlingDeserializer.class);

        assertThat(props.get(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS)).isEqualTo(UUIDDeserializer.class);

        assertThat(props.get(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS)).isEqualTo(JsonDeserializer.class);
    }

    @Test
    void shouldCreateConsumerFactory() {
        ConsumerFactory<Object, Object> factory = config.consumerFactory();

        assertThat(factory).isNotNull();

        assertThat(factory.getConfigurationProperties()).containsEntry(ConsumerConfig.GROUP_ID_CONFIG, "analytics-group");

        assertThat(factory.getConfigurationProperties()).containsEntry(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    }

    @Test
    void shouldCreateContainerFactoryWithGivenConsumerFactory() {
        ConsumerFactory<Object, Object> mockConsumerFactory = mock(ConsumerFactory.class);

        ConcurrentKafkaListenerContainerFactory<Object, Object> resultFactory = config.factory(mockConsumerFactory);

        assertThat(resultFactory).isNotNull();
        assertThat(resultFactory.getConsumerFactory()).isSameAs(mockConsumerFactory);
    }
}