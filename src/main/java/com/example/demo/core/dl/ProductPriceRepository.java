package com.example.demo.core.dl;

import com.example.demo.domain.ProductPrice;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface ProductPriceRepository extends CassandraRepository<ProductPrice> {
    @Query("SELECT * FROM product_price WHERE product_id=?0 LIMIT 1")
    ProductPrice findByProductId(String productId);
}
