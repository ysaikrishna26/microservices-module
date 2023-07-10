package com.microservices.springboot.ingest.product.source.publisher;

import com.microservices.springboot.ingest.product.dto.Product;
import com.microservices.springboot.ingest.product.source.event.DummySourceProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

@Component
public class DummySourceProductEventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummySourceProductEventPublisher.class);

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Value("${publish.dummy.product.delay.ms}")
    private Integer delayMs;

    public void publishCustomEvent(Product product) {
        DummySourceProductEvent dummySourceProductEvent = new DummySourceProductEvent(this, product);
        applicationEventPublisher.publishEvent(dummySourceProductEvent);
    }

    public void publishCustomEventFromProductList(List<Product> productList) throws InterruptedException {
        int max = 10;
        int min = 1;
        int range = max - min + 1;

        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                int randomIndex = (int) (Math.random() * range) + min;
                if (!productList.isEmpty() && randomIndex < productList.size()) {
                    LOGGER.info("Publishing Product List: {} with index: {}", productList.get(randomIndex), randomIndex);
                    this.publishCustomEvent(productList.get(randomIndex));
                }
                sleep(delayMs);
            }
        });
    }
}
