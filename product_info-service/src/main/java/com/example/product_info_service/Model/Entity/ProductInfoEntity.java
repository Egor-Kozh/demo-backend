package com.example.product_info_service.Model.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Builder
@Data
@Table(name = "product_info")
public class ProductInfoEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "product_info_id", updatable = false, nullable = false)
    private UUID productInfoID;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "description")
    private String description;
}
