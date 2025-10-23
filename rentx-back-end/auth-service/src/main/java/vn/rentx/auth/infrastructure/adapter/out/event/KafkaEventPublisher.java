package vn.rentx.auth.infrastructure.adapter.out.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.port.out.EventPublisherPort;
import vn.rentx.auth.domain.base.EventBase;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventPublisher implements EventPublisherPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(EventBase event, String topic) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, json);
            log.info("Published event {} to topic {}", event.getClass().getSimpleName(), topic);
        } catch (Exception e) {
            log.error("Failed to publish event {}", event, e);
            throw new IllegalStateException(e);
        }
    }
}
