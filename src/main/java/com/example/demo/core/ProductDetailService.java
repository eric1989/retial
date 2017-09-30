package com.example.demo.core;

import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public interface ProductDetailService {
    ProductInfoDto.Builder buildProductDetail(@NotNull String productId, @NotNull Set<ProductProperty> properties,
                                              @NotNull ProductInfoDto.Builder builder);
}
