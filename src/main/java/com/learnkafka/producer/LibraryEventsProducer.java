package com.learnkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.LibraryEvent;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

/**
 * LibraryEventsProducer
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 23/02/2025 - 09:12
 * @since 1.17
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class LibraryEventsProducer {

    @Value("${spring.kafka.topic:library-events}")
    private String topic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<Integer, String> kafkaTemplate;

    public CompletableFuture<SendResult<Integer, String>> sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
        var key = libraryEvent.libraryEventId();
        var value = objectMapper.writeValueAsString(libraryEvent);

        var completeFuture = kafkaTemplate.send(topic, key, value);

        return completeFuture
                .whenComplete((sendResult, throwable) -> {
                    if (throwable != null) {
                        handleFailure(key, value, throwable);
                    } else {
                        handleSuccess(key, value, sendResult);
                    }
                });
    }

    private void handleSuccess(final Integer key, final String value, final SendResult<Integer, String> sendResult) {
        log.info("Message sent successfully for the key: {} and the value is {}, partition is {}", key, value, sendResult.getRecordMetadata().partition());
    }

    private void handleFailure(final Integer key, final String value, final Throwable throwable) {
        log.error("Error sending the message and the exception is {}", throwable.getMessage());
    }

}
