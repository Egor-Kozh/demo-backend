package com.example.product_info_service.Model.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Builder
@Data
public class ProductInfoDto {

    private UUID productInfoID;

    @NonNull
    private UUID productId;

    @NonNull
    private String tittle;

    @NonNull
    private String description;
}
