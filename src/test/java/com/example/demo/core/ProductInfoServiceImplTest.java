package com.example.demo.core;

import com.example.demo.domain.ProductPrice;
import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
public class ProductInfoServiceImplTest {
    private static final String PRODUCT_ID = "1";
    private static final String PRODUCT_NAME = "Name";

    @InjectMocks
    private ProductInfoServiceImpl productInfoService;

    @Spy
    private List<ProductDetailService> productDetailServices = new ArrayList<>();

    @Mock
    private ProductDetailService productDetailService;

    @Before
    public void init() {
        productDetailServices.add(productDetailService);
    }

    @Test
    public void testGetProductInfo() {
        doAnswer(new Answer<ProductInfoDto.Builder>() {
            public ProductInfoDto.Builder answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                String productId = (String) args[0];
                Set<ProductProperty> properties = (Set<ProductProperty>) args[1];
                ProductInfoDto.Builder builder1 = (ProductInfoDto.Builder) args[2];
                assertEquals(properties.toArray()[0], ProductProperty.NAME);
                builder1.name(PRODUCT_NAME);
                builder1.productPrice(null);
                assertEquals(productId, PRODUCT_ID);
                return builder1;
            }
        }).when(productDetailService).buildProductDetail(eq(PRODUCT_ID), anySetOf(ProductProperty.class),
                any(ProductInfoDto.Builder.class));
        ProductInfoDto infoDto = productInfoService.getProductInfo(PRODUCT_ID, Collections.singleton(ProductProperty.NAME));
        assertEquals(infoDto.getName(), PRODUCT_NAME);
    }
}
