package com.example.product_info_service.Model.Factory;

import com.example.product_info_service.Model.Dto.ProductInfoDto;
import com.example.product_info_service.Model.Entity.ProductInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductInfoDtoFactory {

    public ProductInfoDto createProductInfoDto(ProductInfoEntity entity) {
        return ProductInfoDto.builder()
                .productInfoID(entity.getProductInfoID())
                .tittle(entity.getTittle())
                .description(entity.getDescription())
                .build();
    }
}
