package com.microservices.springboot.ingest.product.source.event;

import com.microservices.springboot.ingest.product.dto.Product;
import org.springframework.context.ApplicationEvent;

public class DummySourceProductEvent extends ApplicationEvent {

    private final Product product;

    public DummySourceProductEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}
