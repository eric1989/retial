package com.example.demo.dto;

import com.example.demo.domain.CurrencyCode;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ProductPriceDto {
    @NotNull
    private Double value;

    @JsonProperty("currency_code")
    @NotNull
    private CurrencyCode currencyCode;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }
}
