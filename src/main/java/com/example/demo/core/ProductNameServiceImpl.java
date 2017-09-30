package com.example.demo.core;

import com.example.demo.domain.ProductName;
import com.example.demo.dto.ProductInfoDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ProductNameServiceImpl implements ProductRestAPIDetailService {

    @Override
    public ProductInfoDto.Builder buildProductDetail(String productId, String productJson, ProductInfoDto.Builder builder) {
        ProductName productName = new ProductName();
        productName.setProductId(productId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(productJson);
            JsonNode productNameNode = node.at("/product/item/product_description/title");
            String name = mapper.treeToValue(productNameNode, String.class);
            productName.setName(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (productName.getName() != null) {
            builder.name(productName.getName());
        }
        return builder;
    }
}
