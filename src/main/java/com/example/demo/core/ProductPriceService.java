package com.example.demo.core;

import com.example.demo.dto.ProductPriceDto;
import com.example.demo.util.Outcome;

import javax.validation.constraints.NotNull;

public interface ProductPriceService extends ProductDBDetailService {
    Outcome saveProductPrice(@NotNull String productId, @NotNull ProductPriceDto productPriceDto);
}
