package com.example.demo.domain;

import java.util.Objects;

public final class ProductName {
    private String productId;
    private String name;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductName)) {
            return false;
        }

        ProductName that = (ProductName) o;
        return Objects.equals(productId, that.productId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
       return Objects.hash(productId, name);
    }

    @Override
    public String toString() {
        return "Product Id: " + productId + " name: " + name;
    }
}
