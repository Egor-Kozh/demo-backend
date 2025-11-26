package com.example.product_service.Model.Factory;

import com.example.product_service.Model.Dto.ProductDto;
import com.example.product_service.Model.Entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoFactory {

    public ProductDto createProductDto(ProductEntity entity) {
        return ProductDto.builder()
                .productId(entity.getProductId())
                .productName(entity.getProductName())
                .typeProductId(entity.getTypeProductId())
                .companyId(entity.getCompanyId())
                .avrRating(entity.getAvrRating())
                .countRating(entity.getCountRating())
                .price(entity.getPrice())
                .build();
    }
}
