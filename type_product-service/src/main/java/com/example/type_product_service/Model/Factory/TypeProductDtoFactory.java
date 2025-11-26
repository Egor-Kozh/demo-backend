package com.example.type_product_service.Model.Factory;

import com.example.type_product_service.Model.Dto.TypeProductDto;
import com.example.type_product_service.Model.Entity.TypeProductEntity;
import org.springframework.stereotype.Component;

@Component
public class TypeProductDtoFactory {

    public TypeProductDto createTypeProductDto(TypeProductEntity typeProduct) {
        return TypeProductDto.builder()
                .typeProductId(typeProduct.getTypeProductId())
                .typeProductName(typeProduct.getTypeProductName())
                .build();
    }
}
