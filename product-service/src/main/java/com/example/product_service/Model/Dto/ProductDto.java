package com.example.product_service.Model.Dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID productId;

    @NonNull
    private String productName;

    @NonNull
    private UUID typeProductId;

    @NonNull
    private UUID companyId;

    private BigDecimal avrRating;

    private Integer countRating;

    @NonNull
    private BigDecimal price;
}
