package com.example.demo.core;

import com.example.demo.core.dl.ProductPriceRepository;
import com.example.demo.domain.ProductPrice;
import com.example.demo.dto.ProductInfoDto;
import com.example.demo.dto.ProductPriceDto;
import com.example.demo.util.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Override
    public Outcome saveProductPrice(String productId, ProductPriceDto productPriceDto) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setCurrencyCode(productPriceDto.getCurrencyCode());
        productPrice.setProductId(productId);
        productPrice.setValue(productPriceDto.getValue());
        productPriceRepository.save(productPrice);
        return Outcome.SUCCESS;
    }

    @Override
    public ProductInfoDto.Builder buildProductDetail(String productId, ProductInfoDto.Builder builder) {
        ProductPrice productPrice = productPriceRepository.findByProductId(productId);
        if (productPrice != null) {
            ProductPriceDto productPriceDto = new ProductPriceDto();
            productPriceDto.setCurrencyCode(productPrice.getCurrencyCode());
            productPriceDto.setValue(productPrice.getValue());
            builder.productPrice(productPriceDto);
        }
        return builder;
    }
}
