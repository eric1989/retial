package com.example.demo.core;

import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class ProductDBServiceImpl implements ProductDetailService {

    @Autowired
    private Map<ProductProperty, ProductDBDetailService> productDBHandlers;

    @Override
    public ProductInfoDto.Builder buildProductDetail(String productId, Set<ProductProperty> properties,
                                                     ProductInfoDto.Builder builder) {

        for (ProductProperty productProperty : properties) {
            if (productProperty.getSourceType().equals("Database")) {
                productDBHandlers.get(productProperty).buildProductDetail(productId, builder);
            }
        }

        return builder;
    }
}
