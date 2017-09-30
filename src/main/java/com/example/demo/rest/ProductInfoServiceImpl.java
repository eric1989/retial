package com.example.demo.rest;

import com.example.demo.core.ProductInfoService;
import com.example.demo.core.ProductPriceService;
import com.example.demo.domain.ProductProperty;
import com.example.demo.dto.ProductInfoDto;
import com.example.demo.dto.ProductPriceDto;
import com.example.demo.util.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ProductInfoServiceImpl {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductPriceService productPriceService;

    @RequestMapping(method= RequestMethod.GET, value = "/products/{id}/property")
    public ProductInfoDto getProductInfo(@PathVariable String id,
                                         @RequestParam(value="includes",required = false) ProductProperty[] productProperties) {
        Set<ProductProperty> properties = new HashSet<>();

        if (productProperties == null) {
            productProperties = ProductProperty.values();
        }

        properties.addAll(Arrays.asList(productProperties));

        return productInfoService.getProductInfo(id, properties);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}/price/update")
    public Outcome updateProductPrice(@PathVariable String id, @Validated @RequestBody ProductPriceDto productPriceDto) {
        return productPriceService.saveProductPrice(id, productPriceDto);
    }
}
