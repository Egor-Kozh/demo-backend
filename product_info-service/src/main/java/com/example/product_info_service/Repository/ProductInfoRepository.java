package com.example.product_info_service.Repository;

import com.example.product_info_service.Model.Entity.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, UUID> {

    @Query(value = "select * from product_info\n" +
            "where product_info_id = :productInfoId;", nativeQuery = true)
    ProductInfoEntity getProductInfoById(UUID productInfoId);

    @Query(value = "select * from product_info\n" +
            "where product_id = :productId;", nativeQuery = true)
    List<ProductInfoEntity> getProductInfoByProductId(UUID productId);
}
