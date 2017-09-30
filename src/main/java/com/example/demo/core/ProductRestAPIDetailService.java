package com.example.demo.core;

import com.example.demo.dto.ProductInfoDto;

public interface ProductRestAPIDetailService {
    ProductInfoDto.Builder buildProductDetail(String productId, String productJson,
                                             ProductInfoDto.Builder builder);
}
