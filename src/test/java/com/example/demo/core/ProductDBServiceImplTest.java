package com.example.demo.core;

import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class ProductDBServiceImplTest {
    private static final String PRODUCT_ID = "1";

    @InjectMocks
    private ProductDBServiceImpl productDBService;

    @Spy
    private Map<ProductProperty, ProductDBDetailService> productDetailServices = new HashMap<>();

    @Mock
    private ProductDBDetailService productDBDetailService;

    @Before
    public void setUp()  {
        productDetailServices.put(ProductProperty.PRICE, productDBDetailService);
    }

    @Test
    public void testBuildProductDetail() {
        ProductInfoDto.Builder builder = new ProductInfoDto.Builder();
        builder.id(PRODUCT_ID);
        productDBService.buildProductDetail(PRODUCT_ID, Collections.singleton(ProductProperty.PRICE), builder);

        verify(productDBDetailService).buildProductDetail(eq(PRODUCT_ID), eq(builder));
    }
}
