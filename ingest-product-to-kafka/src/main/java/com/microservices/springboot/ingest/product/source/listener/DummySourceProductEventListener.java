package com.microservices.springboot.ingest.product.source.listener;

import com.microservices.springboot.ingest.product.source.event.DummySourceProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DummySourceProductEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummySourceProductEventListener.class);

    @EventListener
    public void handleDummySourceProductEvent(DummySourceProductEvent dummySourceProductEvent) {
        LOGGER.info("Event Listener!!!: {}", dummySourceProductEvent.getProduct());
    }
}
