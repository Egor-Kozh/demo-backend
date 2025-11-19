package com.example.type_product_service.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "type_product")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeProductEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "type_product_id", updatable = false, nullable = false)
    private UUID typeProductId;

    @Column(name = "type_product_name", nullable = false)
    private String typeProductName;
}
