package com.example.demo.core;

import com.example.demo.domain.ProductName;
import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class ProductRestAPIServiceImpl implements ProductDetailService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Map<ProductProperty, ProductRestAPIDetailService> productRestAPIDetailServiceMap;

    private static final String productServiceHostName = "http://redsky.target.com/v2/pdp/tcin/{productId}?excludes={excludes}";

    private static final String excludeInfo = "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

    @Override
    public ProductInfoDto.Builder buildProductDetail(String productId, Set<ProductProperty> properties,
                                                     ProductInfoDto.Builder builder) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("productId", productId);
        uriVariables.put("excludes", excludeInfo);

        String productJson = restTemplate.getForObject(productServiceHostName, String.class, uriVariables);

        for (ProductProperty productProperty : properties) {
            if (productProperty.getSourceType().equals("Rest API")) {
                productRestAPIDetailServiceMap.get(productProperty).buildProductDetail(productId, productJson, builder);
            }
        }

        return builder;
    }
}
