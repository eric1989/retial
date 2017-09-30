package com.example.demo.core;

import com.example.demo.dto.ProductInfoDto;

public interface ProductDBDetailService {
    ProductInfoDto.Builder buildProductDetail(String productId, ProductInfoDto.Builder builder);
}
