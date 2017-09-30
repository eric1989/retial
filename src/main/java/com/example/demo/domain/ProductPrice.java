package com.example.demo.domain;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table(value = "product_price")
public final class ProductPrice {
    @PrimaryKeyColumn(name = "id", ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private UUID id = UUIDs.timeBased();

    @PrimaryKeyColumn(name = "product_id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String productId;

    @Column(value = "value")
    private Double value;

    @Column(value = "currency_code")
    private CurrencyCode currencyCode;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductPrice)) {
            return false;
        }

        ProductPrice that = (ProductPrice) o;
        return Objects.equals(productId, that.productId) && Objects.equals(id, that.id)
                && Objects.equals(value, that.value) && Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, id, value, currencyCode);
    }
}
