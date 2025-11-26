package com.example.product_service.Repository;

import com.example.product_service.Model.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "select * from product\n" +
            "where product_id = :productId", nativeQuery = true)
    ProductEntity getProductById(UUID productId);

    @Query(value = "select * from product\n" +
            "where product_name = :productName", nativeQuery = true)
    ProductEntity getProductByName(String productName);
}
