package com.microservices.springboot.ingest.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {

    private List<Product> products;

    private Integer total;

    private Integer skip;

    private Integer limit;

}
