package com.example.type_product_service.Repository;

import com.example.type_product_service.Model.Entity.TypeProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TypeProductRepository extends JpaRepository<TypeProductEntity, UUID> {

    @Query(value = "select * from type_product\n" +
            "where type_product_id = :typeProductId", nativeQuery = true)
    TypeProductEntity findTypeProductById(UUID typeProductId);

    @Query(value = "select * from type_product\n" +
            "where type_product_name = :typeProductName", nativeQuery = true)
    TypeProductEntity findTypeProductByName(String typeProductName);
}
