package com.example.demo.core;

import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private List<ProductDetailService> productDetailServices;


    @Override
    public ProductInfoDto getProductInfo(String productId, Set<ProductProperty> properties) {
        ProductInfoDto.Builder builder = new ProductInfoDto.Builder();
        builder.id(productId);
        productDetailServices.forEach(productDetailService -> productDetailService.buildProductDetail(productId, properties, builder));
        return builder.build();
    }

}
