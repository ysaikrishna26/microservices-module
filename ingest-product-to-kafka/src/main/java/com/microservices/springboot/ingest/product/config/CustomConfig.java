package com.microservices.springboot.ingest.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ingest.product.service")
public class CustomConfig {
    private String welcomeMessage;
}
