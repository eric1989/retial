package com.example.demo.core;

import com.example.demo.core.dl.ProductPriceRepository;
import com.example.demo.domain.CurrencyCode;
import com.example.demo.domain.ProductPrice;
import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import com.example.demo.dto.ProductPriceDto;
import com.example.demo.util.Outcome;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceServiceImplTest {

    private static final String PRODUCT_ID = "1";

    @InjectMocks
    private ProductPriceServiceImpl productPriceService;

    @Mock
    private ProductPriceRepository productPriceRepository;

    @Captor
    private ArgumentCaptor<ProductPrice> productPriceCaptor;

    @Test
    public void testGetNullResult() {
        when(productPriceRepository.findByProductId(PRODUCT_ID)).thenReturn(null);
        ProductInfoDto.Builder builder = new ProductInfoDto.Builder();
        builder.id(PRODUCT_ID);
        productPriceService.buildProductDetail(PRODUCT_ID, builder);
        ProductInfoDto infoDto = builder.build();
        assertEquals(infoDto.getProductPriceDto(), null);
    }

    @Test
    public void testGetProductPrice() {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setValue(10.0);
        productPrice.setProductId(PRODUCT_ID);
        productPrice.setCurrencyCode(CurrencyCode.USD);
        when(productPriceRepository.findByProductId(PRODUCT_ID)).thenReturn(productPrice);
        ProductInfoDto.Builder builder = new ProductInfoDto.Builder();
        builder.id(PRODUCT_ID);
        productPriceService.buildProductDetail(PRODUCT_ID, builder);
        ProductInfoDto infoDto = builder.build();
        assertEquals(productPrice.getCurrencyCode(), infoDto.getProductPriceDto().getCurrencyCode());
        assertEquals(productPrice.getValue(), infoDto.getProductPriceDto().getValue());
    }

    @Test
    public void testSaveProductPrice() {
        ProductPriceDto productPriceDto = new ProductPriceDto();
        productPriceDto.setCurrencyCode(CurrencyCode.USD);
        productPriceDto.setValue(10.0);

        Outcome outcome = productPriceService.saveProductPrice(PRODUCT_ID, productPriceDto);
        verify(productPriceRepository).save(productPriceCaptor.capture());
        assertEquals(Outcome.SUCCESS, outcome);
        assertEquals(productPriceCaptor.getValue().getProductId(), PRODUCT_ID);
        assertEquals(productPriceCaptor.getValue().getCurrencyCode(), productPriceDto.getCurrencyCode());
        assertEquals(productPriceCaptor.getValue().getValue(), productPriceDto.getValue());
    }
}
