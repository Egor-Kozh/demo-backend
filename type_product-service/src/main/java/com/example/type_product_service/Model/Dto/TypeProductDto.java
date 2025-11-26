package com.example.type_product_service.Model.Dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeProductDto {

    private UUID typeProductId;

    @NonNull
    private String typeProductName;
}
