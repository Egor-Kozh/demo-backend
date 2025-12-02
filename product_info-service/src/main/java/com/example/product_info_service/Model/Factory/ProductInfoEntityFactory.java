package com.example.product_info_service.Model.Factory;

import com.example.product_info_service.Model.Dto.ProductInfoDto;
import com.example.product_info_service.Model.Entity.ProductInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductInfoEntityFactory {

    public ProductInfoEntity createProductInfoEntity(ProductInfoDto dto) {
        return ProductInfoEntity.builder()
                .productId(dto.getProductId())
                .tittle(dto.getTittle())
                .description(dto.getDescription())
                .build();
    }
}
