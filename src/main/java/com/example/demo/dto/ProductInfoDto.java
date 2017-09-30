package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInfoDto {
    @NotNull
    private String id;
    @NotNull
    private String name;
    @JsonProperty("current_price")
    private ProductPriceDto productPriceDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPriceDto getProductPriceDto() {
        return productPriceDto;
    }

    public void setProductPriceDto(ProductPriceDto productPriceDto) {
        this.productPriceDto = productPriceDto;
    }

    public static class Builder {
        private String id;
        private String name;
        private ProductPriceDto productPriceDto;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder productPrice(ProductPriceDto productPriceDto) {
            this.productPriceDto = productPriceDto;
            return this;
        }

        public ProductInfoDto build() {
            ProductInfoDto productInfoDto = new ProductInfoDto();
            productInfoDto.setId(this.id);
            productInfoDto.setName(this.name);
            productInfoDto.setProductPriceDto(productPriceDto);
            return productInfoDto;
        }
    }
}
