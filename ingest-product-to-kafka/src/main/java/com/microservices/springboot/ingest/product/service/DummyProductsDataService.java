package com.microservices.springboot.ingest.product.service;

import com.microservices.springboot.ingest.product.dto.Product;
import com.microservices.springboot.ingest.product.dto.ProductListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DummyProductsDataService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyProductsDataService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dummy.product.url}")
    private String productUrl;

    @Value("${dummy.product.get.product.limit}")
    private Integer productLimit;

    public void getProductDummyData() {
        ResponseEntity<ProductListResponse> productListResponse = restTemplate.getForEntity(productUrl + "?limit={limit}", ProductListResponse.class, productLimit);
        LOGGER.info(String.valueOf(productListResponse.getBody()));
    }
}
