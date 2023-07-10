package com.microservices.springboot.ingest.product;

import com.microservices.springboot.ingest.product.config.CustomConfig;
import com.microservices.springboot.ingest.product.dto.Product;
import com.microservices.springboot.ingest.product.dto.ProductListResponse;
import com.microservices.springboot.ingest.product.source.publisher.DummySourceProductEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class IngestProductToKafkaApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngestProductToKafkaApplication.class);

    private final CustomConfig customConfig;

    private final DummySourceProductEventPublisher dummySourceProductEventPublisher;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dummy.product.url}")
    private String productUrl;

    @Value("${dummy.product.get.product.limit}")
    private Integer productLimit;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(IngestProductToKafkaApplication.class);
        springApplication.run(args);
    }

    public IngestProductToKafkaApplication(CustomConfig customConfig, DummySourceProductEventPublisher dummySourceProductEventPublisher) {
        this.customConfig = customConfig;
        this.dummySourceProductEventPublisher = dummySourceProductEventPublisher;
    }

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info(customConfig.getWelcomeMessage());

        ResponseEntity<ProductListResponse> productListResponse = restTemplate.getForEntity(productUrl + "?limit={limit}", ProductListResponse.class, productLimit);
        if (productListResponse.getStatusCode().equals(HttpStatus.OK)) {
            Optional<List<Product>> optProductList = Optional.ofNullable(productListResponse.getBody()).map(ProductListResponse::getProducts);
            optProductList.ifPresent(productList -> {
                try {
                    LOGGER.info("Product List Fetched from Dummy API: {}", productList);
                    dummySourceProductEventPublisher.publishCustomEventFromProductList(productList);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        LOGGER.info("Publishing Dummy Product has been handed over to new executor thread!!!: {}", Thread.currentThread().getName());
    }
}
