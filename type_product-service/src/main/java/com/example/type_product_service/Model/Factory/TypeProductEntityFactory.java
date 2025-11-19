package com.example.type_product_service.Model.Factory;

import com.example.type_product_service.Model.Dto.TypeProductDto;
import com.example.type_product_service.Model.Entity.TypeProductEntity;
import org.springframework.stereotype.Component;

@Component
public class TypeProductEntityFactory {

    public TypeProductEntity createTypeProductEntity(TypeProductDto typeProduct) {
        return TypeProductEntity.builder()
                .typeProductName(typeProduct.getTypeProductName())
                .build();
    }
}
