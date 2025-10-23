package vn.rentx.auth.application.port.out;

import vn.rentx.auth.domain.base.EventBase;

public interface EventPublisherPort {

    /**
     * Publishes an asynchronous event.
     *
     * @param event The event object (DomainEvent / IntegrationEvent)
     * @param topicOrKey The topic name, exchange, or routing-key (depending on the adapter)
     */
    void publish(EventBase event, String topicOrKey);

}
