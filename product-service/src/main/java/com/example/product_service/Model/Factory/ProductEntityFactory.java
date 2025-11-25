package com.example.product_service.Model.Factory;

import com.example.product_service.Model.Dto.ProductDto;
import com.example.product_service.Model.Entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductEntityFactory {

    public ProductEntity createProductEntity(ProductDto dto) {
        return ProductEntity.builder()
                .productName(dto.getProductName())
                .typeProductId(dto.getTypeProductId())
                .companyId(dto.getCompanyId())
                .avrRating(BigDecimal.valueOf(0))
                .countRating(0)
                .price(dto.getPrice())
                .build();
    }
}
