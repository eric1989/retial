package com.example.demo.core;

import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public interface ProductInfoService {
    ProductInfoDto getProductInfo(@NotNull String productId, Set<ProductProperty> properties);
}
