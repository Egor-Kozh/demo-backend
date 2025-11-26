package com.example.product_service.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "product_id", updatable = false, nullable = false)
    private UUID productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "type_product_id", nullable = false)
    private UUID typeProductId;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "avr_rating")
    private BigDecimal avrRating;

    @Column(name = "count_rating")
    private Integer countRating;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
