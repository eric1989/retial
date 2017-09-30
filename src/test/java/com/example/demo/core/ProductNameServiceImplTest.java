package com.example.demo.core;

import com.example.demo.dto.ProductInfoDto;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ProductNameServiceImplTest{

    private static final String PRODUCT_ID = "13860428";
    private static final String PRODUCT_NAME = "The Big Lebowski (Blu-ray)";

    @InjectMocks
    private ProductNameServiceImpl productNameService;

    private String jsonString;

    @Before
    public void setUp() throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("product.json");
        jsonString = IOUtils.toString(inputStream, "UTF-8");
    }

    @Test
    public void testProductName() {
        ProductInfoDto.Builder builder = new ProductInfoDto.Builder();
        builder.id(PRODUCT_ID);
        productNameService.buildProductDetail(PRODUCT_ID, jsonString, builder);
        ProductInfoDto productInfoDto = builder.build();
        assertEquals(productInfoDto.getName(), PRODUCT_NAME);
        assertEquals(productInfoDto.getId(), PRODUCT_ID);
    }
}
