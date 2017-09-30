package com.example.demo.core;

import com.example.demo.domain.ProductName;
import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductRestAPIServiceImplTest {
    private static final String PRODUCT_ID = "13860428";
    private static final String PRODUCT_NAME = "The Big Lebowski (Blu-ray)";
    private static final String API_URL = "http://redsky.target.com/v2/pdp/tcin/{productId}?excludes={excludes}";
    private static final String API_EXCLUDES_PROPERTY = "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

    @InjectMocks
    private ProductRestAPIServiceImpl productNameService;

    @Mock
    private RestTemplate restTemplate;

    @Spy
    private Map<ProductProperty, ProductRestAPIDetailService> productDetailServices = new HashMap<>();

    @Mock
    private ProductRestAPIDetailService productRestAPIDetailService;

    private String jsonString;

    @Before
    public void setUp() throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("product.json");
        jsonString = IOUtils.toString(inputStream, "UTF-8");
        productDetailServices.put(ProductProperty.NAME, productRestAPIDetailService);
    }

    @Test
    public void testGetProductName() {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("productId", PRODUCT_ID);
        uriVariables.put("excludes", API_EXCLUDES_PROPERTY);

        ProductName expect = new ProductName();
        expect.setName(PRODUCT_NAME);
        expect.setProductId(PRODUCT_ID);
        when(restTemplate.getForObject(API_URL, String.class, uriVariables)).thenReturn(jsonString);
        ProductInfoDto.Builder builder = new ProductInfoDto.Builder();
        builder.id(PRODUCT_ID);
        productNameService.buildProductDetail(PRODUCT_ID, Collections.singleton(ProductProperty.NAME),builder);

        verify(productRestAPIDetailService).buildProductDetail(eq(PRODUCT_ID), eq(jsonString), any(ProductInfoDto.Builder.class));
    }
}
